package com.example.nucleofornari.presentation.screen.responsavel.reunioes_solicitadas

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

class ReunioesSolicitadasViewModel(
    private val sessaoUsuario: com.example.nucleofornari.domain.model.SessaoUsuario,
    private val api: UsuarioApiService,
    private val appContext: Context
) : ViewModel() {

    var uiStateReunioes by mutableStateOf<UiState<List<com.example.nucleofornari.domain.model.agendamento.AgendamentoDto>>>(UiState.Loading)
        private set

    init {
        getReunioes()
    }

    companion object {
        private const val CACHE_KEY = "reunioes_solicitadas_cache"
        private const val CACHE_VALIDITY = 1 * 60 * 1000L
    }

    private fun getReunioes() {
        viewModelScope.launch {
            uiStateReunioes = UiState.Loading
            val cached: List<com.example.nucleofornari.domain.model.agendamento.AgendamentoDto>? = CacheUtils.ler(appContext, CACHE_KEY, CACHE_VALIDITY)

            if (cached != null) {
                uiStateReunioes = UiState.Success(cached)
                return@launch
            }
            try {
                val response = api.getAgendamentosPorUsuario(sessaoUsuario.userId)
                if(response.isSuccessful){
                    val reunioes = response.body()
                    if(reunioes.isNullOrEmpty()){
                        uiStateReunioes = UiState.Empty
                    }else{
                        uiStateReunioes = UiState.Success(reunioes)
                        CacheUtils.salvar(appContext, CACHE_KEY, reunioes)
                    }
                }else if (response.code() == 204) {
                    uiStateReunioes = UiState.Empty
                }

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
