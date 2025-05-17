package com.example.nucleofornari.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Inicio: BottomBarScreen(
        route = "inicio",
        title = "Início",
        icon = Icons.Default.Home
    )
    object Chamado: BottomBarScreen(
        route = "chamado",
        title = "Chamados",
        icon = Icons.Default.Call
    )
    object Relatorio: BottomBarScreen(
        route = "relatorio",
        title = "Relatórios",
        icon = Icons.Default.Create
    )

    object Agenda: BottomBarScreen(
        route = "agenda",
        title = "Agenda",
        icon = Icons.Default.DateRange
    )
    object Publicacao: BottomBarScreen(
        route = "publicacoes",
        title = "Publicações",
        icon = Icons.Default.Create
    )
    object Reuniao: BottomBarScreen(
        route = "reunioes",
        title = "Reuniões",
        icon = Icons.Default.Email
    )
}