package com.example.nucleofornari.presentation.screen.professor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nucleofornari.data.model.chamado.TipoChamadoDto
import com.example.nucleofornari.data.remote.service.UsuarioApiService
import com.example.nucleofornari.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

open class CategoriasViewModel (
    private val api: UsuarioApiService,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<TipoChamadoDto>>>(UiState.Empty)
    val uiState: StateFlow<UiState<List<TipoChamadoDto>>> = _uiState

    fun findCategorias() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            try {
                val response = api.findTiposChamado()
                _uiState.value = UiState.Success(response)

            } catch (e: IOException) {
                _uiState.value = UiState.Error("Erro de conexão. Verifique sua internet.")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Erro inesperado: ${e.message}")
            }
        }
    }

}