package com.example.nucleofornari.data.model.usuario

import com.example.nucleofornari.data.model.endereco.EnderecoDto
import java.time.LocalDate

data class UsuarioCreateDto(
    val nome: String,
    val cpf: String,
    val telefone: String,
    val email: String,
    val dtNasc: String,
    val funcao: String,
    val endereco: EnderecoDto
)
