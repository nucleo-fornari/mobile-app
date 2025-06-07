package com.example.nucleofornari.domain.model

data class SessaoUsuario(
    var userId: Int = 0,
    var nome: String = "",
    var email: String = "",
    var funcao: String = "",
    var telefone: String = "",
    var salaId: Int? = null,
    var token: String = ""
)
