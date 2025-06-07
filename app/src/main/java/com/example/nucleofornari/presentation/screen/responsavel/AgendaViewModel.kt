package com.example.nucleofornari.presentation.screen.responsavel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nucleofornari.data.model.SessaoUsuario
import com.example.nucleofornari.data.model.aluno.AlunoResponseDto
import com.example.nucleofornari.data.remote.service.UsuarioApiService
import com.example.nucleofornari.presentation.screen.professor.CategoriasViewModel
import com.example.nucleofornari.presentation.screen.professor.RelatorioProfessorViewModel
import com.example.nucleofornari.presentation.screen.professor.RelatorioProfessorViewModel.Companion
import com.example.nucleofornari.util.CacheUtils
import com.example.nucleofornari.util.ErrorUtils
import com.example.nucleofornari.util.UiState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AgendaViewModel(
    private val sessaoUsuario: SessaoUsuario,
    private val api: UsuarioApiService,
    private val appContext: Context
) : ViewModel() {

    private val _uiStateAfiliados = mutableStateOf<UiState<List<AlunoResponseDto>>>(UiState.Loading)
    val uiStateAfiliados: State<UiState<List<AlunoResponseDto>>> = _uiStateAfiliados

    var alunoSelecionado by mutableStateOf<AlunoResponseDto?>(null)
        private set

    companion object {
        private const val CACHE_KEY = "afiliados_cache"
        private const val CACHE_VALIDITY = 60 * 60 * 1000L
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


            val cached: List<AlunoResponseDto>? = CacheUtils.ler(appContext, CACHE_KEY, CACHE_VALIDITY)

            if (cached != null) {
                _uiStateAfiliados.value = UiState.Success(cached)
                return@launch
            }

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
                CacheUtils.salvar(appContext, CACHE_KEY, afiliadosMapeados)
            } catch (e: IOException) {
                _uiStateAfiliados.value = UiState.Error("Erro de conexão. Verifique sua internet.")
            } catch (e: Exception) {
                val errorMessage = when (e) {
                    is HttpException -> {
                        val errorBody = e.response()?.errorBody()
                        if (errorBody != null)
                            ErrorUtils.parseErrorMessage(errorBody)
                        else
                            "Erro desconhecido do servidor"
                    }
                    else -> "Erro inesperado: ${e.message}"
                }

                _uiStateAfiliados.value = UiState.Error(errorMessage)
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
