package com.example.nucleofornari.presentation.screen.professor

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nucleofornari.data.model.SessaoUsuario
import com.example.nucleofornari.data.model.agendamento.AgendamentoDto
import com.example.nucleofornari.data.model.chamado.ChamadoDto
import com.example.nucleofornari.data.remote.service.UsuarioApiService
import com.example.nucleofornari.util.CacheUtils
import com.example.nucleofornari.util.ErrorUtils
import com.example.nucleofornari.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

class ChamadosViewModel (
    private val sessaoUsuario: SessaoUsuario,
    private val api: UsuarioApiService,
    private val appContext: Context
) : ViewModel() {

    var uiStateChamados by mutableStateOf<UiState<List<ChamadoDto>>>(UiState.Loading)
        private set

    private val _createChamadoState = MutableStateFlow<UiState<ChamadoDto?>>(UiState.Empty)
    val createChamadoUiState: StateFlow<UiState<ChamadoDto?>> = _createChamadoState

    companion object {
        private const val CACHE_KEY = "chamados_cache"
        private const val CACHE_VALIDITY = 5 * 60 * 1000L
    }

    init {
        listChamadosById()
    }

    fun createChamado(chamado: ChamadoDto) {
        viewModelScope.launch {
            _createChamadoState.value = UiState.Loading

            try {
                api.createChamado(chamado, sessaoUsuario.userId)
                _createChamadoState.value = UiState.Success(null)
            } catch (e: IOException) {
                _createChamadoState.value = UiState.Error("Erro de conexão. Verifique sua internet.")
            } catch (e: Exception) {
                val errorMessage = when (e) {
                    is HttpException -> {
                        val errorBody = e.response()?.errorBody()
                        if (errorBody != null)
                            ErrorUtils.parseErrorMessage(errorBody)
                        else
                            "Erro desconhecido do servidor"
                    }
                    else -> "Erro inesperado: ${e.message}"
                }

                _createChamadoState.value = UiState.Error(errorMessage)
            }



        }
    }

    fun listChamadosById() {
        viewModelScope.launch {

            val cached: List<ChamadoDto>? = CacheUtils.ler(appContext, CACHE_KEY, CACHE_VALIDITY)
            if (cached != null) {
                uiStateChamados = UiState.Success(cached)
                return@launch
            }

            try {
                val chamados = api.listChamados(sessaoUsuario.userId)
                uiStateChamados = UiState.Success(chamados)

                CacheUtils.salvar(appContext, CACHE_KEY, chamados)
            } catch (e: IOException) {
                uiStateChamados = UiState.Error("Erro de conexão. Verifique sua internet.")
            } catch (e: Exception) {
                val errorMessage = when (e) {
                    is HttpException -> {
                        val errorBody = e.response()?.errorBody()
                        if (errorBody != null)
                            ErrorUtils.parseErrorMessage(errorBody)
                        else
                            "Erro desconhecido do servidor"
                    }
                    else -> "Erro inesperado: ${e.message}"
                }

                uiStateChamados = UiState.Error(errorMessage)
            }
        }
    }

    fun resetCreateChamadoUiState() {
        _createChamadoState.value = UiState.Empty
    }

}