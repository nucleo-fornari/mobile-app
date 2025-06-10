package com.example.nucleofornari.presentation.screen.professor.chamados

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nucleofornari.data.remote.UsuarioApiService
import com.example.nucleofornari.domain.model.chamado.ChamadoDto
import com.example.nucleofornari.util.CacheUtils
import com.example.nucleofornari.util.ErrorUtils
import com.example.nucleofornari.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ChamadosViewModel (
    private val sessaoUsuario: com.example.nucleofornari.domain.model.SessaoUsuario,
    private val api: UsuarioApiService,
    private val appContext: Context
) : ViewModel() {

    var uiStateChamados by mutableStateOf<UiState<List<com.example.nucleofornari.domain.model.chamado.ChamadoDto>>>(UiState.Loading)
        private set

    private val _createChamadoState = MutableStateFlow<UiState<com.example.nucleofornari.domain.model.chamado.ChamadoDto?>>(UiState.Empty)
    val createChamadoUiState: StateFlow<UiState<com.example.nucleofornari.domain.model.chamado.ChamadoDto?>> = _createChamadoState

    companion object {
        private const val CACHE_KEY = "chamados_cache"
        private const val CACHE_VALIDITY = 1 * 60 * 1000L
    }

    init {
        listChamadosById()
    }

    fun createChamado(chamado: com.example.nucleofornari.domain.model.chamado.ChamadoDto) {
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
                val response = api.listChamados(sessaoUsuario.userId)

                if (response.isSuccessful) {
                    val chamados = response.body()
                    if (chamados.isNullOrEmpty()) {
                        uiStateChamados = UiState.Empty

                    } else {
                        uiStateChamados = UiState.Success(chamados)
                        CacheUtils.salvar(appContext, CACHE_KEY, chamados)
                    }
                } else if (response.code() == 204) {
                    uiStateChamados = UiState.Empty
                } else {
                    val errorBody = response.errorBody()
                    val message = if (errorBody != null)
                        ErrorUtils.parseErrorMessage(errorBody)
                    else
                        "Erro desconhecido do servidor"

                    uiStateChamados = UiState.Error(message)
                }

            } catch (e: IOException) {
                uiStateChamados = UiState.Error("Erro de conexão. Verifique sua internet.")
            } catch (e: Exception) {
                uiStateChamados = UiState.Error("Erro inesperado: ${e.message}")
            }

        }
    }


    fun resetCreateChamadoUiState() {
        _createChamadoState.value = UiState.Empty
    }

}