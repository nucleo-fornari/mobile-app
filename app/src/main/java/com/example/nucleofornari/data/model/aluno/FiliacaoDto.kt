package com.example.nucleofornari.data.model.aluno

import com.example.nucleofornari.data.model.usuario.UsuarioResponseDto

data class FiliacaoDto(
    val responsavel: UsuarioResponseDto,
    val parentesco: String
)

