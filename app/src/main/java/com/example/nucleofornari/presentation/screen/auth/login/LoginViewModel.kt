package com.example.nucleofornari.presentation.screen.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nucleofornari.data.model.SessaoUsuario
import com.example.nucleofornari.data.model.usuario.UsuarioLoginDto
import com.example.nucleofornari.data.model.usuario.UsuarioTokenDto
import com.example.nucleofornari.data.remote.RetrofitClient
import com.example.nucleofornari.data.remote.UsuarioApiService
import com.example.nucleofornari.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

open class LoginViewModel(private val usuarioApi: UsuarioApiService, private var sessaoUsuario: SessaoUsuario) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<UsuarioTokenDto>>(UiState.Empty)
    val uiState: StateFlow<UiState<UsuarioTokenDto>> = _uiState

    fun login(email: String, senha: String) {
        val loginDto = UsuarioLoginDto(email, senha)

        viewModelScope.launch {
            _uiState.value = UiState.Loading

            try {
                val response = usuarioApi.login(loginDto)

                sessaoUsuario.userId = response.userId
                sessaoUsuario.nome = response.nome
                sessaoUsuario.email = response.email
                sessaoUsuario.funcao = response.funcao
                sessaoUsuario.salaId = response.salaId
                sessaoUsuario.token = response.token

                _uiState.value = UiState.Success(response)
            } catch (e: IOException) {
                _uiState.value = UiState.Error("Erro de conexão. Verifique sua internet.")
            } catch (e: HttpException) {
                _uiState.value = UiState.Error("Erro de autenticação. Verifique os dados.")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Erro inesperado: ${e.message}")
            }
        }
    }
}