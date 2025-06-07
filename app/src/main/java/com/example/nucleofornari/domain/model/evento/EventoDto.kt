package com.example.nucleofornari.domain.model.evento

import com.example.nucleofornari.domain.model.sala.SalaDto
import com.example.nucleofornari.domain.model.usuario.UsuarioResponseDto
import java.time.LocalDateTime

data class EventoDto(
    val id: Int,
    val titulo: String,
    val descricao: String,
    val data: String,
    val local: String,
    val tipo: String,
    val responsavel: com.example.nucleofornari.domain.model.usuario.UsuarioResponseDto,
    val salas: List<com.example.nucleofornari.domain.model.sala.SalaDto>,
    val encerrado: Boolean
)