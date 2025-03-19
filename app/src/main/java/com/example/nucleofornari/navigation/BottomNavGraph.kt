package com.example.nucleofornari.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nucleofornari.screens.AbrirChamadoScreen
import com.example.nucleofornari.screens.CategoriasScreen
import com.example.nucleofornari.screens.ChamadoEnviadoScreen
import com.example.nucleofornari.screens.ChamadoProfessorScreen
import com.example.nucleofornari.screens.InicioProfessorScreen
import com.example.nucleofornari.screens.RelatorioProfessorScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Inicio.route){
        composable(route = BottomBarScreen.Inicio.route){
            InicioProfessorScreen()
        }
        composable(route = BottomBarScreen.Chamado.route){
            ChamadoProfessorScreen(navController)
        }
        composable(route = BottomBarScreen.Relatorio.route){
            RelatorioProfessorScreen()
        }
        composable("abrir_chamado"){
            AbrirChamadoScreen(navController)
        }
        composable("selecionar_categoria"){
            CategoriasScreen(navController)
        }
        composable("chamado_enviado"){
            ChamadoEnviadoScreen(navController)
        }
    }
}