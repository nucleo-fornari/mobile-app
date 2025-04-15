package com.example.nucleofornari.data.model.sala

import com.example.nucleofornari.data.model.aluno.AlunoResponseDto
import com.example.nucleofornari.data.model.usuario.UsuarioResponseDto

data class SalaDto(
    val id: Int,
    val localizacao: String,
    val nome: String,
    val professores: List<UsuarioResponseDto>,
    val alunos: List<AlunoResponseDto>, // cuidado com recursividade, mas ok se for só para leitura
    val grupo: GrupoDto
)

