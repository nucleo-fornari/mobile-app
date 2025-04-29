package com.example.nucleofornari.data.model.aluno

import com.example.nucleofornari.data.model.avaliacao.AvaliacaoDto
import com.example.nucleofornari.data.model.recado.RecadoDto
import com.example.nucleofornari.data.model.restricao.RestricaoDto
import com.example.nucleofornari.data.model.sala.SalaDto
import java.time.LocalDate

data class AlunoResponseDto(
    val id: Int,
    val ra: String,
    val nome: String,
    val laudado: Boolean,
    val laudoNome: String?,
    val dtNasc: String,
    val filiacoes: List<FiliacaoDto>,
    val observacoes: String?,
    val restricoes: List<RestricaoDto>,
    val sala: SalaDto?, // Pode vir null (dentro do aluno, tem casos com "sala": null)
    val recados: List<RecadoDto>,
    val avaliacoes: List<AvaliacaoDto>
)

