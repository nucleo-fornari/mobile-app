package com.example.nucleofornari.presentation.screen.responsavel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nucleofornari.data.model.SessaoUsuario
import com.example.nucleofornari.data.remote.UsuarioApiService
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AgendaViewModel(
    private val sessaoUsuario: SessaoUsuario,
    private val api: UsuarioApiService
) : ViewModel() {

    var nomeAfiliado by mutableStateOf("")
        private set

    var recados by mutableStateOf(listOf<Recado>())
        private set

    init {
        getAfiliado()
    }

    private fun getAfiliado() {
        viewModelScope.launch {
            try {
                val response = api.getUsuarioPorId(sessaoUsuario.userId)
                if (response.afiliados.isNotEmpty()) {
                    val afiliado = response.afiliados[0]

                    nomeAfiliado = afiliado.nome

                    recados = afiliado.recados.map {
                        Recado(
                            id = it.id,
                            titulo = it.titulo,
                            descricao = it.conteudo,
                            autor = it.responsavel.nome,
                            data = formatarData(it.dtCriacao)
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("AgendaViewModel", "Erro ao buscar afiliado: ${e.message}")
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
