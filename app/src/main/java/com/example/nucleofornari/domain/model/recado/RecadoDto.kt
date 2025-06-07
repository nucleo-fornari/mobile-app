package com.example.nucleofornari.domain.model.recado

import com.example.nucleofornari.domain.model.usuario.UsuarioResponseDto
import java.time.LocalDateTime

data class RecadoDto(
    val id: Int,
    val titulo: String,
    val conteudo: String,
    val responsavel: com.example.nucleofornari.domain.model.usuario.UsuarioResponseDto,
    val alunoNome: String,
    val dtCriacao: String
)

