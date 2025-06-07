package com.example.nucleofornari.presentation.screen.responsavel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nucleofornari.data.model.SessaoUsuario
import com.example.nucleofornari.data.model.agendamento.AgendamentoDto
import com.example.nucleofornari.data.model.evento.EventoDto
import com.example.nucleofornari.data.remote.service.UsuarioApiService
import com.example.nucleofornari.presentation.screen.responsavel.PublicacoesViewModel.Companion
import com.example.nucleofornari.util.CacheUtils
import com.example.nucleofornari.util.ErrorUtils
import com.example.nucleofornari.util.UiState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ReunioesSolicitadasViewModel(
    private val sessaoUsuario: SessaoUsuario,
    private val api: UsuarioApiService,
    private val appContext: Context
) : ViewModel() {

    var uiStateReunioes by mutableStateOf<UiState<List<AgendamentoDto>>>(UiState.Loading)
        private set

    init {
        getReunioes()
    }

    companion object {
        private const val CACHE_KEY = "reunioes_solicitadas_cache"
        private const val CACHE_VALIDITY = 5 * 60 * 1000L
    }

    private fun getReunioes() {
        viewModelScope.launch {
            uiStateReunioes = UiState.Loading
            val cached: List<AgendamentoDto>? = CacheUtils.ler(appContext, CACHE_KEY, CACHE_VALIDITY)

            if (cached != null) {
                uiStateReunioes = UiState.Success(cached)
                return@launch
            }
            try {
                val reunioes = api.getAgendamentosPorUsuario(sessaoUsuario.userId)
                uiStateReunioes = UiState.Success(reunioes)
                CacheUtils.salvar(appContext, CACHE_KEY, reunioes)
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
