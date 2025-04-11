package com.example.nucleofornari.data.model

data class SessaoUsuario(
    var userId: Int?,
    var nome:String?,
    var email: String?,
    var funcao: String?,
    var salaId: Int?,
    var token:String?
)