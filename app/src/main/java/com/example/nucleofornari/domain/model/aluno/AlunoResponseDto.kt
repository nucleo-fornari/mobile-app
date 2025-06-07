package com.example.nucleofornari.domain.model.aluno

import com.example.nucleofornari.domain.model.avaliacao.AvaliacaoDto
import com.example.nucleofornari.domain.model.recado.RecadoDto
import com.example.nucleofornari.domain.model.restricao.RestricaoDto
import com.example.nucleofornari.domain.model.sala.SalaDto
import java.time.LocalDate

data class AlunoResponseDto(
    val id: Int,
    val ra: String,
    val nome: String,
    val laudado: Boolean,
    val laudoNome: String?,
    val dtNasc: String,
    val filiacoes: List<com.example.nucleofornari.domain.model.aluno.FiliacaoDto>,
    val observacoes: String?,
    val restricoes: List<com.example.nucleofornari.domain.model.restricao.RestricaoDto>,
    val sala: com.example.nucleofornari.domain.model.sala.SalaDto?, // Pode vir null (dentro do aluno, tem casos com "sala": null)
    val recados: List<com.example.nucleofornari.domain.model.recado.RecadoDto>,
    val avaliacoes: List<com.example.nucleofornari.domain.model.avaliacao.AvaliacaoDto>
)

