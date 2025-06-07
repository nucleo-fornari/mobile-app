package com.example.nucleofornari.domain.model.aluno

import com.example.nucleofornari.domain.model.usuario.UsuarioResponseDto

data class FiliacaoDto(
    val responsavel: com.example.nucleofornari.domain.model.usuario.UsuarioResponseDto,
    val parentesco: String
)

