package com.example.nucleofornari.data.model.usuario

data class UsuarioTokenDto(
    val userId: Int?,
    val nome: String?,
    val email: String?,
    val funcao: String?,
    val salaId: Int?,
    val token: String?
)
