package com.example.nucleofornari.presentation.screen.responsavel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nucleofornari.data.model.SessaoUsuario
import com.example.nucleofornari.data.model.agendamento.AgendamentoDto
import com.example.nucleofornari.data.remote.service.UsuarioApiService
import com.example.nucleofornari.util.ErrorUtils
import com.example.nucleofornari.util.UiState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ReunioesSolicitadasViewModel(
    private val sessaoUsuario: SessaoUsuario,
    private val api: UsuarioApiService
) : ViewModel() {

    var uiStateReunioes by mutableStateOf<UiState<List<AgendamentoDto>>>(UiState.Loading)
        private set

    init {
        getReunioes()
    }

    private fun getReunioes() {
        viewModelScope.launch {
            uiStateReunioes = UiState.Loading
            try {
                val reunioes = api.getAgendamentosPorUsuario(sessaoUsuario.userId)
                uiStateReunioes = UiState.Success(reunioes)

            } catch (e: IOException) {
                uiStateReunioes = UiState.Error("Erro de conexão. Verifique sua internet.")
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()
                val errorMessage = if (errorBody != null) {
                    ErrorUtils.parseErrorMessage(errorBody)
                } else {
                    "Erro desconhecido do servidor"
                }
                uiStateReunioes = UiState.Error(errorMessage)
            } catch (e: Exception) {
                uiStateReunioes = UiState.Error("Erro inesperado: ${e.message}")
            }
        }
    }
}
