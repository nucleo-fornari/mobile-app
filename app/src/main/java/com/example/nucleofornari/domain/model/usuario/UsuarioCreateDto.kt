package com.example.nucleofornari.domain.model.usuario

import com.example.nucleofornari.domain.model.endereco.EnderecoDto
import java.time.LocalDate

data class UsuarioCreateDto(
    val nome: String,
    val cpf: String,
    val telefone: String,
    val email: String,
    val dtNasc: String,
    val funcao: String,
    val endereco: com.example.nucleofornari.domain.model.endereco.EnderecoDto
)
