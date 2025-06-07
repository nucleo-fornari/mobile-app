package com.example.nucleofornari.presentation.screen.responsavel.publicacoes

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
import com.example.nucleofornari.util.UiState.Success
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class PublicacoesViewModel(
    private val sessaoUsuario: com.example.nucleofornari.domain.model.SessaoUsuario,
    private val api: UsuarioApiService,
    private val appContext: Context
) : ViewModel() {

    var uiStateEventos by mutableStateOf<UiState<List<com.example.nucleofornari.domain.model.evento.EventoDto>>>(UiState.Loading)
        private set

    init {
        getPublicacoes()
    }

    companion object {
        private const val CACHE_KEY = "eventos_cache"
        private const val CACHE_VALIDITY = 5 * 60 * 1000L
    }

    private fun getPublicacoes() {
        viewModelScope.launch {
            uiStateEventos = UiState.Loading

            val cached: List<com.example.nucleofornari.domain.model.evento.EventoDto>? = CacheUtils.ler(appContext, CACHE_KEY, CACHE_VALIDITY)

            if (cached != null) {
                uiStateEventos = Success(cached)
                return@launch
            }

            try {
                val usuario = api.getUsuarioPorId(sessaoUsuario.userId)
                val salasUnicas = usuario.afiliados.mapNotNull { it.sala?.id }.distinct()

                val eventos = mutableListOf<com.example.nucleofornari.domain.model.evento.EventoDto>()
                salasUnicas.forEach { salaId ->
                    val eventosDaSala = api.getEventosPorSala(salaId)
                    eventos.addAll(eventosDaSala)
                }

                val eventosUnicos = eventos.distinctBy { it.id }

                uiStateEventos = Success(eventosUnicos)
                CacheUtils.salvar(appContext, CACHE_KEY, eventosUnicos)
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
