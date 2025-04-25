package com.example.nucleofornari.presentation.common.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
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

    @Composable
    fun Home(color: Color) {
        Icon(
            Icons.Filled.Home,
            contentDescription = "Início",
            tint = color
        )
    }

    @Composable
    fun Menu(color: Color) {
        Icon(
            Icons.Filled.Menu,
            contentDescription = "Menu",
            tint = color
        )
    }

//    @Composable
//    fun Inbox(color: Color) {
//        Icon(
//            Icons.Filled.Inbox,
//            contentDescription = "Caixa de entrada",
//            tint = color
//        )
//    }
//
//    @Composable
//    fun Description(color: Color) {
//        Icon(
//            Icons.Filled.Description,
//            contentDescription = "Descrição",
//            tint = color
//        )
//    }

//    @Composable
//    fun Pending(color: Color) {
//        Icon(
//            Icons.Outlined.Clo,
//            contentDescription = "Concluído", tint = color
//        )
//    }
}