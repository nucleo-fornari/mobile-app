package com.example.nucleofornari.presentation.screen.responsavel.reunioes

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nucleofornari.data.remote.UsuarioApiService
import com.example.nucleofornari.util.CacheUtils
import com.example.nucleofornari.util.ErrorUtils
import com.example.nucleofornari.util.UiState
import com.example.nucleofornari.util.convertMillisToIso
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ReunioesViewModel(
    private val sessaoUsuario: com.example.nucleofornari.domain.model.SessaoUsuario,
    private val api: UsuarioApiService,
    private val appContext: Context
) : ViewModel() {

    private val _uiStateAfiliados = mutableStateOf<UiState<List<com.example.nucleofornari.domain.model.aluno.AlunoResponseDto>>>(UiState.Loading)
    val uiStateAfiliados: State<UiState<List<com.example.nucleofornari.domain.model.aluno.AlunoResponseDto>>> = _uiStateAfiliados

    companion object {
        private const val CACHE_KEY = "afiliados_cache"
        private const val CACHE_VALIDITY = 60 * 60 * 1000L
    }

    var alunoSelecionado by mutableStateOf<com.example.nucleofornari.domain.model.aluno.AlunoResponseDto?>(null)
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

    fun selecionarAluno(aluno: com.example.nucleofornari.domain.model.aluno.AlunoResponseDto) {
        alunoSelecionado = aluno
    }

    init {
        getAfiliados()
    }

    private fun getAfiliados() {
        viewModelScope.launch {
            _uiStateAfiliados.value = UiState.Loading

            val cached: List<com.example.nucleofornari.domain.model.aluno.AlunoResponseDto>? = CacheUtils.ler(appContext, CACHE_KEY, CACHE_VALIDITY)

            if (cached != null) {
                _uiStateAfiliados.value = UiState.Success(cached)
                return@launch
            }

            try {
                val response = api.getUsuarioPorId(sessaoUsuario.userId)
                val afiliadosMapeados = response.afiliados.map {
                    com.example.nucleofornari.domain.model.aluno.AlunoResponseDto(
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
                CacheUtils.salvar(appContext, CACHE_KEY, afiliadosMapeados)
            } catch (e: IOException) {
                _uiStateAfiliados.value = UiState.Error("Erro de conexão. Verifique sua internet.")
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()
                val errorMessage = if (errorBody != null) {
                    ErrorUtils.parseErrorMessage(errorBody)
                } else {
                    "Erro desconhecido do servidor"
                }
                _uiStateAfiliados.value = UiState.Error(errorMessage)
            } catch (e: Exception) {
                _uiStateAfiliados.value = UiState.Error("Erro inesperado: ${e.message}")
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

        val agendamento = com.example.nucleofornari.domain.model.agendamento.AgendamentoDto(
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
            } catch (e: IOException) {
                onError(IOException("Erro de conexão. Verifique sua internet."))
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()
                val errorMessage = if (errorBody != null) {
                    ErrorUtils.parseErrorMessage(errorBody)
                } else {
                    "Erro desconhecido do servidor"
                }
                onError(Exception("Erro ao criar agendamento: $errorMessage"))
            } catch (e: Exception) {
                onError(Exception("Erro inesperado: ${e.message}"))
            }
        }
    }
}
