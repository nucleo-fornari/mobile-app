package com.example.nucleofornari.presentation.screen.responsavel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nucleofornari.data.model.SessaoUsuario
import com.example.nucleofornari.data.model.evento.EventoDto
import com.example.nucleofornari.data.remote.service.UsuarioApiService
import com.example.nucleofornari.util.ErrorUtils
import com.example.nucleofornari.util.UiState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class PublicacoesViewModel(
    private val sessaoUsuario: SessaoUsuario,
    private val api: UsuarioApiService
) : ViewModel() {

    var uiStateEventos by mutableStateOf<UiState<List<EventoDto>>>(UiState.Loading)
        private set

    init {
        getPublicacoes()
    }

    private fun getPublicacoes() {
        viewModelScope.launch {
            uiStateEventos = UiState.Loading
            try {
                val usuario = api.getUsuarioPorId(sessaoUsuario.userId)
                val salasUnicas = usuario.afiliados.mapNotNull { it.sala?.id }.distinct()

                val eventos = mutableListOf<EventoDto>()
                salasUnicas.forEach { salaId ->
                    val eventosDaSala = api.getEventosPorSala(salaId)
                    eventos.addAll(eventosDaSala)
                }

                // Remover duplicatas pelo ID (ou algum outro campo único)
                val eventosUnicos = eventos.distinctBy { it.id }

                uiStateEventos = UiState.Success(eventosUnicos)

            } catch (e: IOException) {
                uiStateEventos = UiState.Error("Erro de conexão. Verifique sua internet.")
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()
                val errorMessage = if (errorBody != null) {
                    ErrorUtils.parseErrorMessage(errorBody)
                } else {
                    "Erro desconhecido do servidor"
                }
                uiStateEventos = UiState.Error(errorMessage)
            } catch (e: Exception) {
                uiStateEventos = UiState.Error("Erro inesperado: ${e.message}")
            }
        }
    }
}
