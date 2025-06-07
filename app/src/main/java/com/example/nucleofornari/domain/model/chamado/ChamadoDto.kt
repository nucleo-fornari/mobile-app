package com.example.nucleofornari.domain.model.chamado

import com.example.nucleofornari.domain.model.usuario.UsuarioResponseDto

data class ChamadoDto(
    val id: Int? = null,
    var descricao: String = "",
    val finalizado: Boolean? = null,
    val dtAbertura: String = "",
    var tipo: com.example.nucleofornari.domain.model.chamado.TipoChamadoDto? = null,
    val dtFechamento: String = "",
    var criancaAtipica: Boolean = false,
    val responsavel: com.example.nucleofornari.domain.model.usuario.UsuarioResponseDto? = null
)
