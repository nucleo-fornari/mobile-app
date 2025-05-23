package com.example.nucleofornari.ui.theme.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object AppIcons {

    @Composable
    fun CheckCircle(color: Color) {
        Icon(
            Icons.Filled.CheckCircle,
            contentDescription = "Concluído",
            tint = color
        )
    }

    @Composable
    fun ArrowBack(color: Color) {
        Icon(
            Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Voltar",
            tint = color

        )
    }

    @Composable
    fun ArrowForward(color: Color) {
        Icon(
            Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "Avançar",
            tint = color

        )
    }

//    @Composable
//    fun Pending(color: Color) {
//        Icon(
//            Icons.Outlined.Clo,
//            contentDescription = "Concluído", tint = color
//        )
//    }
}