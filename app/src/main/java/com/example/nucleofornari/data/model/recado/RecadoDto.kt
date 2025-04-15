package com.example.nucleofornari.data.model.recado

import com.example.nucleofornari.data.model.usuario.UsuarioResponseDto
import java.time.LocalDateTime

data class RecadoDto(
    val id: Int,
    val titulo: String,
    val conteudo: String,
    val responsavel: UsuarioResponseDto,
    val alunoNome: String,
    val dtCriacao: LocalDateTime
)

