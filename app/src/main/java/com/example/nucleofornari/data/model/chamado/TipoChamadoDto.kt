package com.example.nucleofornari.data.model.chamado

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TipoChamadoDto(
    val id: Int? = null,
    val tipo: String = "",
    val prioridade: Int? = null
) : Parcelable