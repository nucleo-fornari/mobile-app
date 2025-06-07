package com.example.nucleofornari.domain.model.usuario

import com.example.nucleofornari.domain.model.aluno.AlunoResponseDto
import com.example.nucleofornari.domain.model.endereco.EnderecoDto
import java.time.LocalDate

data class UsuarioResponseDto(
    val id: Int,
    val nome: String,
    val cpf: String,
    val telefone: String,
    val email: String,
    val dtNasc: String,
    val funcao: String,
    val afiliados: List<com.example.nucleofornari.domain.model.aluno.AlunoResponseDto> = emptyList(),
    val endereco: com.example.nucleofornari.domain.model.endereco.EnderecoDto
)
