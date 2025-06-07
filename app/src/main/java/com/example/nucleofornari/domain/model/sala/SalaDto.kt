package com.example.nucleofornari.domain.model.sala

import com.example.nucleofornari.domain.model.aluno.AlunoResponseDto
import com.example.nucleofornari.domain.model.usuario.UsuarioResponseDto

data class SalaDto(
    val id: Int,
    val localizacao: String,
    val nome: String,
    val professores: List<com.example.nucleofornari.domain.model.usuario.UsuarioResponseDto>,
    val alunos: List<com.example.nucleofornari.domain.model.aluno.AlunoResponseDto>, // cuidado com recursividade, mas ok se for só para leitura
    val grupo: com.example.nucleofornari.domain.model.sala.GrupoDto
)

