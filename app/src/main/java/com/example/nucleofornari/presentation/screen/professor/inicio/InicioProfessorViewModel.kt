package com.example.nucleofornari.presentation.screen.professor.inicio

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nucleofornari.data.remote.UsuarioApiService
import com.example.nucleofornari.util.CacheUtils
import com.example.nucleofornari.util.ErrorUtils
import com.example.nucleofornari.util.UiState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class InicioProfessorViewModel (
    private val sessaoUsuario: com.example.nucleofornari.domain.model.SessaoUsuario,
    private val api: UsuarioApiService,
    private val appContext: Context
) : ViewModel() {

    var uiStateAlunos by mutableStateOf<UiState<List<com.example.nucleofornari.domain.model.aluno.AlunoResponseDto>>>(UiState.Loading)
        private set

    init {
        getAlunos()
    }

    companion object {
        private const val CACHE_KEY = "alunos_cache"
        private const val CACHE_VALIDITY = 1 * 60 * 1000L
    }

    private fun getAlunos() {
        viewModelScope.launch {

            val cached: List<com.example.nucleofornari.domain.model.aluno.AlunoResponseDto>? = CacheUtils.ler(appContext, CACHE_KEY, CACHE_VALIDITY)
            if (cached != null) {
                uiStateAlunos = UiState.Success(cached)
                return@launch
            }

            try {
                val salaId = sessaoUsuario.salaId
                when {
                    salaId != null -> {
                        val sala = api.getSalaPorId(salaId)
                        uiStateAlunos = UiState.Success(sala.alunos)
                        CacheUtils.salvar(appContext, CACHE_KEY, sala.alunos)
                    }
                    else -> {
                        uiStateAlunos = UiState.Error("ID da sala não encontrado para o professor logado.")
                    }
                }
            } catch (e: IOException) {
                uiStateAlunos = UiState.Error("Erro de conexão. Verifique sua internet.")
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

                uiStateAlunos = UiState.Error(errorMessage)
            }

        }
    }

}