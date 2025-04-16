package com.example.nucleofornari.data.model.avaliacao

import java.time.LocalDate

data class AvaliacaoDto(
    val id: Int,
    val periodo: String,
    val bimestre: String,
    val textoSocioAfetivaEmocional: String,
    val textoFisicoMotora: String,
    val ano: String,
    val textoCognitiva: String,
    val dtCriacao: String
)

