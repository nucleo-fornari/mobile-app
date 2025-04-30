package com.example.nucleofornari.presentation.screen.professor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nucleofornari.data.model.SessaoUsuario
import com.example.nucleofornari.data.model.aluno.AlunoResponseDto
import com.example.nucleofornari.data.model.chamado.ChamadoDto
import com.example.nucleofornari.data.model.evento.EventoDto
import com.example.nucleofornari.data.remote.UsuarioApiService
import com.example.nucleofornari.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InicioProfessorViewModel (
    private val sessaoUsuario: SessaoUsuario,
    private val api: UsuarioApiService
) : ViewModel() {

    var uiStateAlunos by mutableStateOf<UiState<List<AlunoResponseDto>>>(UiState.Loading)
        private set

    init {
        getAlunos()
    }

    private fun getAlunos() {
        viewModelScope.launch {
            try {
                val salaId = sessaoUsuario.salaId
                when {
                    salaId != null -> {
                        val sala = api.getSalaPorId(salaId)
                        uiStateAlunos = UiState.Success(sala.alunos)
                    }
                    else -> {
                        uiStateAlunos = UiState.Error("ID da sala não encontrado para o professor logado.")
                    }
                }
            } catch (e: Exception) {
                uiStateAlunos = UiState.Error("Erro ao carregar alunos da sala: ${e.message}")
            }

        }
    }

}