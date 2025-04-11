package com.example.nucleofornari.data.model

data class SessaoUsuario(
    val userId: Int?,
    var nome:String = "",
    val email: String?,
    val funcao: String?,
    val salaId: Int?,
    var token:String = ""
)