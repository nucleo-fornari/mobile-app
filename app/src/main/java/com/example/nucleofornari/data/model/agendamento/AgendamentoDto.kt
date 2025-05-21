package com.example.nucleofornari.data.model.agendamento

data class AgendamentoDto(
    val id: Int? = null,
    var responsavelId: Int? = null,
    val responsavelNome: String = "",
    val salaId: Int? = null,
    var motivo: String = "",
    val aceito: Boolean = false,
    var descricao: String = "",
    val data: String = ""
)
