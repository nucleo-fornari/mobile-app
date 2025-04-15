package com.example.nucleofornari.presentation.screen.responsavel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nucleofornari.data.model.SessaoUsuario
import com.example.nucleofornari.data.remote.UsuarioApiService

class AgendaViewModel(
    private val sessaoUsuario: SessaoUsuario,
    private val api: UsuarioApiService
) : ViewModel() {

    var nomeAfiliado by mutableStateOf("")
        private set

    init {
        getAfiliado()
    }

    private fun getAfiliado() {
        viewModelScope.launch {
            try {
                val response = api.getUsuarioPorId(sessaoUsuario.userId)
                if (response.afiliados.isNotEmpty()) {
                    nomeAfiliado = response.afiliados[0].nome
                }
            } catch (e: Exception) {
                Log.e("AgendaViewModel", "Erro ao buscar afiliado: ${e.message}")
            }
        }
    }
}
