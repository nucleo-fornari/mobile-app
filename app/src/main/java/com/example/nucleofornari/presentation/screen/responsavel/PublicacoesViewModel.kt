package com.example.nucleofornari.presentation.screen.responsavel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nucleofornari.data.model.SessaoUsuario
import com.example.nucleofornari.data.model.aluno.AlunoResponseDto
import com.example.nucleofornari.data.model.evento.EventoDto
import com.example.nucleofornari.data.model.recado.RecadoDto
import com.example.nucleofornari.data.remote.UsuarioApiService
import com.example.nucleofornari.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

            } catch (e: Exception) {
                uiStateEventos = UiState.Error("Erro ao carregar publicações: ${e.message}")
            }
        }
    }
}

