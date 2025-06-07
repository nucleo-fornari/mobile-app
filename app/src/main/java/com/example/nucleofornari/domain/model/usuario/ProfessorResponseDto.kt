package com.example.nucleofornari.domain.model.usuario

import com.example.nucleofornari.domain.model.endereco.EnderecoDto
import java.time.LocalDate

data class ProfessorResponseDto(
    val id: Int,
    val nome: String,
    val cpf: String,
    val telefone: String,
    val email: String,
    val dtNasc: LocalDate,
    val funcao: String,
    val endereco: com.example.nucleofornari.domain.model.endereco.EnderecoDto,
    val sala: com.example.nucleofornari.domain.model.usuario.ProfessorResponseDto.SalaProfessorDto
) {
    data class SalaProfessorDto(
        val id: Int,
        val localizacao: String,
        val nome: String
    )
}
