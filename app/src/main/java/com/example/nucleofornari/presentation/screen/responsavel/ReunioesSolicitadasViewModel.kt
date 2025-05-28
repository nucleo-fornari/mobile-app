package com.example.nucleofornari.presentation.screen.responsavel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nucleofornari.data.model.SessaoUsuario
import com.example.nucleofornari.data.model.agendamento.AgendamentoDto
import com.example.nucleofornari.data.model.chamado.ChamadoDto
import com.example.nucleofornari.data.model.evento.EventoDto
import com.example.nucleofornari.data.remote.service.UsuarioApiService
import com.example.nucleofornari.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReunioesSolicitadasViewModel (
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
            try {
                val reunioes = api.getAgendamentosPorUsuario(sessaoUsuario.userId)

                uiStateReunioes = UiState.Success(reunioes)

            } catch (e: Exception) {
                uiStateReunioes = UiState.Error("Erro ao carregar reuniões solicitadas: ${e.message}")
            }
        }
    }

}