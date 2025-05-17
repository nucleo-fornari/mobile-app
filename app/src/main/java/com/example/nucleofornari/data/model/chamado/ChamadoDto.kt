package com.example.nucleofornari.data.model.chamado

import com.example.nucleofornari.data.model.usuario.UsuarioResponseDto

data class ChamadoDto(
    val id: Int? = null,
    var descricao: String = "",
    val finalizado: Boolean? = null,
    val dtAbertura: String = "",
    var tipo: TipoChamadoDto? = null,
    val dtFechamento: String = "",
    var criancaAtipica: Boolean = false,
    val responsavel: UsuarioResponseDto? = null
)
