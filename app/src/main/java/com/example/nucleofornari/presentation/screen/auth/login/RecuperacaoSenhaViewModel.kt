package com.example.nucleofornari.presentation.screen.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nucleofornari.data.remote.service.UsuarioApiService
import com.example.nucleofornari.util.ErrorUtils
import com.example.nucleofornari.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class RecuperacaoSenhaViewModel (
    private val api: UsuarioApiService
) : ViewModel() {

    private val _solicitarRedefinicaoUiState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val solicitarRedefinicaoUiState: StateFlow<UiState<Unit>> = _solicitarRedefinicaoUiState

    private val _enviarTokenUiState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val enviarCodigoUiState: StateFlow<UiState<Unit>> = _enviarTokenUiState

    private val _redefinirSenhaUiState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val redefinirSenhaUiStare: StateFlow<UiState<Unit>> = _redefinirSenhaUiState

    fun solicitarRedefinicao(email: String) {

        viewModelScope.launch {
            _solicitarRedefinicaoUiState.value = UiState.Loading
            try {
                api.esqueciSenha(email)
                _solicitarRedefinicaoUiState.value = UiState.Success(Unit)
            } catch (e: HttpException) {
                val errorMessage = e.response()?.errorBody()?.let { ErrorUtils.parseErrorMessage(it) }
                    ?: "Erro desconhecido"
                _solicitarRedefinicaoUiState.value = UiState.Error(errorMessage)
            } catch (e: IOException) {
                _solicitarRedefinicaoUiState.value = UiState.Error("Erro de conexão")
            } catch (e: Exception) {
                _solicitarRedefinicaoUiState.value = UiState.Error("Erro inesperado")
            }
        }
    }

    fun enviarToken(token: String) {
        viewModelScope.launch {
            _enviarTokenUiState.value = UiState.Loading
            try {
                api.tokenRedefinicaoSenha(token)
                _enviarTokenUiState.value = UiState.Success(Unit)
            } catch (e: HttpException) {
                val errorMessage = e.response()?.errorBody()?.let { ErrorUtils.parseErrorMessage(it) }
                    ?: "Erro desconhecido"
                _enviarTokenUiState.value = UiState.Error(errorMessage)
            } catch (e: IOException) {
                _enviarTokenUiState.value = UiState.Error("Erro de conexão")
            } catch (e: Exception) {
                _enviarTokenUiState.value = UiState.Error("Erro inesperado")
            }
        }
    }

    fun redefinirSenha(token: String, senha: String, senha2: String, email: String) {
        viewModelScope.launch {
            _redefinirSenhaUiState.value = UiState.Loading

            if (senha != senha2) {
                _redefinirSenhaUiState.value = UiState.Error("As senhas devem ser iguais")
                return@launch
            }

            try {
                api.redefinirSenha(token, email, senha)
                _redefinirSenhaUiState.value = UiState.Success(Unit)
            } catch (e: HttpException) {
                val errorMessage = e.response()?.errorBody()?.let { ErrorUtils.parseErrorMessage(it) }
                    ?: "Erro desconhecido"
                _redefinirSenhaUiState.value = UiState.Error(errorMessage)
            } catch (e: IOException) {
                _redefinirSenhaUiState.value = UiState.Error("Erro de conexão")
            } catch (e: Exception) {
                _redefinirSenhaUiState.value = UiState.Error("Erro inesperado")
            }
        }
    }

    fun resetSolicitarSenhaUiState() {
        _solicitarRedefinicaoUiState.value = UiState.Empty
    }

    fun resetEnviarCodigoUiState() {
        _enviarTokenUiState.value = UiState.Empty
    }

    fun reserRedefinirSenhaUiState() {
        _redefinirSenhaUiState.value = UiState.Empty
    }
}