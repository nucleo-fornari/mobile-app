package com.example.nucleofornari.data.model.usuario

import com.example.nucleofornari.data.model.endereco.EnderecoDto
import java.time.LocalDate

data class ProfessorResponseDto(
    val id: Int,
    val nome: String,
    val cpf: String,
    val telefone: String,
    val email: String,
    val dtNasc: LocalDate,
    val funcao: String,
    val endereco: EnderecoDto,
    val sala: SalaProfessorDto
) {
    data class SalaProfessorDto(
        val id: Int,
        val localizacao: String,
        val nome: String
    )
}
