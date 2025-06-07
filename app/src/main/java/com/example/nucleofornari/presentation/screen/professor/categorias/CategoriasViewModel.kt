package com.example.nucleofornari.presentation.screen.professor.categorias

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nucleofornari.data.remote.UsuarioApiService
import com.example.nucleofornari.util.CacheUtils
import com.example.nucleofornari.util.ErrorUtils
import com.example.nucleofornari.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

open class CategoriasViewModel (
    private val api: UsuarioApiService,
    private val appContext: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<com.example.nucleofornari.domain.model.chamado.TipoChamadoDto>>>(UiState.Empty)
    val uiState: StateFlow<UiState<List<com.example.nucleofornari.domain.model.chamado.TipoChamadoDto>>> = _uiState

    companion object {
        private const val CACHE_KEY = "categorias_cache"
        private const val CACHE_VALIDITY = 5 * 60 * 1000L // 5 minutos
    }

    fun findCategorias() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            val cached: List<com.example.nucleofornari.domain.model.chamado.TipoChamadoDto>? = CacheUtils.ler(appContext, CACHE_KEY, CACHE_VALIDITY)
            if (cached != null) {
                _uiState.value = UiState.Success(cached)
                return@launch
            }

            try {
                val response = api.findTiposChamado()
                _uiState.value = UiState.Success(response)
                CacheUtils.salvar(appContext, CACHE_KEY, response)
            } catch (e: IOException) {
                _uiState.value = UiState.Error("Erro de conexão. Verifique sua internet.")
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

                _uiState.value = UiState.Error(errorMessage)
            }
        }
    }

}