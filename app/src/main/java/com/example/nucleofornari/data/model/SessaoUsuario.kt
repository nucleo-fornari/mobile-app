package com.example.nucleofornari.data.model

data class SessaoUsuario(
    var userId: Int = 0,
    var nome: String = "",
    var email: String = "",
    var funcao: String = "",
    var salaId: Int? = null,
    var token: String = ""
)
