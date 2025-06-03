package com.example.nucleofornari.presentation.screen.professor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nucleofornari.data.model.SessaoUsuario
import com.example.nucleofornari.data.model.chamado.ChamadoDto
import com.example.nucleofornari.data.remote.service.UsuarioApiService
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
    private val api: UsuarioApiService
) : ViewModel() {

    private val _createChamadoState = MutableStateFlow<UiState<ChamadoDto?>>(UiState.Empty)
    val createChamadoUiState: StateFlow<UiState<ChamadoDto?>> = _createChamadoState

    private val _listChamadosByIdUiState = MutableStateFlow<UiState<List<ChamadoDto>>>(UiState.Empty)
    val listChamadosByIdUiState: StateFlow<UiState<List<ChamadoDto>>> = _listChamadosByIdUiState

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
            _listChamadosByIdUiState.value = UiState.Loading
            try {
                _listChamadosByIdUiState.value = UiState.Success(api.listChamados(sessaoUsuario.userId))
            } catch (e: IOException) {
                _listChamadosByIdUiState.value = UiState.Error("Erro de conexão. Verifique sua internet.")
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

                _listChamadosByIdUiState.value = UiState.Error(errorMessage)
            }
        }
    }

    fun resetCreateChamadoUiState() {
        _createChamadoState.value = UiState.Empty
    }

}