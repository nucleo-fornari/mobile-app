package com.example.nucleofornari.domain.model.evento

import java.time.LocalDateTime

data class EventoCriacaoReqDto(
    val titulo: String,
    val descricao: String,
    val data: String,
    val tipo: String,
    val usuarioId: Int,
    val salas: List<Int>?
)
