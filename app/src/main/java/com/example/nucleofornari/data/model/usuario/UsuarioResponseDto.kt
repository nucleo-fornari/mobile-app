package com.example.nucleofornari.data.model.usuario

import com.example.nucleofornari.data.model.aluno.AlunoResponseDto
import com.example.nucleofornari.data.model.endereco.EnderecoDto
import java.time.LocalDate

data class UsuarioResponseDto(
    val id: Int,
    val nome: String,
    val cpf: String,
    val telefone: String,
    val email: String,
    val dtNasc: LocalDate,
    val funcao: String,
    val afiliados: List<AlunoResponseDto> = emptyList(),
    val endereco: EnderecoDto
)
