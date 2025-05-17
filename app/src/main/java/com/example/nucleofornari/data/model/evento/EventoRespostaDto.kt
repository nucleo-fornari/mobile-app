package com.example.nucleofornari.data.model.evento

import com.example.nucleofornari.data.model.sala.SalaDto
import com.example.nucleofornari.data.model.usuario.UsuarioResponseDto
import java.time.LocalDateTime

data class EventoRespostaDto(
    val id: Int?,
    val titulo: String,
    val descricao: String,
    val data: String,
    val local: String?,
    val tipo: String,
    val responsavel: UsuarioResponseDto?,
    val salas: List<SalaDto>?,
    val encerrado: Boolean?
)
