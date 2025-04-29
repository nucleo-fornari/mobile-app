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
import com.example.nucleofornari.data.model.recado.RecadoDto
import com.example.nucleofornari.data.remote.UsuarioApiService
import com.example.nucleofornari.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AgendaViewModel(
    private val sessaoUsuario: SessaoUsuario,
    private val api: UsuarioApiService
) : ViewModel() {

    private val _uiStateAfiliados = mutableStateOf<UiState<List<AlunoResponseDto>>>(UiState.Loading)
    val uiStateAfiliados: State<UiState<List<AlunoResponseDto>>> = _uiStateAfiliados

    var alunoSelecionado by mutableStateOf<AlunoResponseDto?>(null)
        private set

    fun selecionarAluno(aluno: AlunoResponseDto) {
        alunoSelecionado = aluno
    }

    init {
        getAfiliados()
    }

    private fun getAfiliados() {
        viewModelScope.launch {
            _uiStateAfiliados.value = UiState.Loading
            try {
                val response = api.getUsuarioPorId(sessaoUsuario.userId)
                val afiliadosMapeados = response.afiliados.map {
                    AlunoResponseDto(
                        id = it.id,
                        ra = it.ra,
                        nome = it.nome,
                        laudado = it.laudado,
                        laudoNome = it.laudoNome,
                        dtNasc = it.dtNasc,
                        filiacoes = it.filiacoes,
                        observacoes = it.observacoes,
                        restricoes = it.restricoes,
                        sala = it.sala,
                        recados = it.recados,
                        avaliacoes = it.avaliacoes
                    )
                }

                // Selecionar o primeiro afiliado automaticamente
                if (afiliadosMapeados.isNotEmpty()) {
                    alunoSelecionado = afiliadosMapeados.first()
                }

                _uiStateAfiliados.value = UiState.Success(afiliadosMapeados)
            } catch (e: Exception) {
                _uiStateAfiliados.value = UiState.Error("Erro ao buscar afiliado: ${e.message}")
            }
        }

    }

    private fun formatarData(dataIso: String): String {
        return try {
            val localDateTime = LocalDateTime.parse(dataIso)
            localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        } catch (e: Exception) {
            "Data inválida"
        }
    }
}
