package com.example.nucleofornari.presentation.screen.responsavel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nucleofornari.data.model.SessaoUsuario
import com.example.nucleofornari.data.model.agendamento.AgendamentoDto
import com.example.nucleofornari.data.model.aluno.AlunoResponseDto
import com.example.nucleofornari.data.remote.service.UsuarioApiService
import com.example.nucleofornari.util.UiState
import com.example.nucleofornari.util.convertMillisToIso
import kotlinx.coroutines.launch

class ReunioesViewModel(
    private val sessaoUsuario: SessaoUsuario,
    private val api: UsuarioApiService
) : ViewModel() {

    private val _uiStateAfiliados = mutableStateOf<UiState<List<AlunoResponseDto>>>(UiState.Loading)
    val uiStateAfiliados: State<UiState<List<AlunoResponseDto>>> = _uiStateAfiliados

    var alunoSelecionado by mutableStateOf<AlunoResponseDto?>(null)
        private set

    var categoriaSelecionada by mutableStateOf<String?>(null)
        private set

    var descricao by mutableStateOf("")
        private set

    var dataIsoFormatada by mutableStateOf("")
        private set

    fun atualizarData(millis: Long) {
        dataIsoFormatada = convertMillisToIso(millis)
    }

    fun atualizarDescricao(novaDescricao: String) {
        descricao = novaDescricao
    }


    fun selecionarCategoria(categoria: String) {
        categoriaSelecionada = categoria
    }

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

                _uiStateAfiliados.value = UiState.Success(afiliadosMapeados)
            } catch (e: Exception) {
                _uiStateAfiliados.value = UiState.Error("Erro ao buscar afiliado: ${e.message}")
            }
        }

    }

    fun criarAgendamento(
        salaId: Int,
        motivo: String,
        descricao: String,
        dataEmMillis: Long,
        onSuccess: () -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val responsavelId = sessaoUsuario.userId

        val agendamento = AgendamentoDto(
            responsavelId = responsavelId,
            salaId = salaId,
            motivo = motivo,
            descricao = descricao,
            data = convertMillisToIso(dataEmMillis)
        )

        viewModelScope.launch {
            try {
                api.createAgendamento(agendamento)
                onSuccess()
            } catch (e: Exception) {
                onError(e)
            }
        }
    }


}
