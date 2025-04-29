package com.example.nucleofornari.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nucleofornari.presentation.screen.auth.login.LoginScreen
import com.example.nucleofornari.presentation.screen.auth.login.LoginViewModel
import com.example.nucleofornari.presentation.screen.professor.ChamadoProfessorScreen
import com.example.nucleofornari.presentation.screen.professor.ChamadosViewModel
import com.example.nucleofornari.screens.InicioProfessorScreen
import com.example.nucleofornari.screens.RelatorioProfessorScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Inicio.route){
        composable(route = BottomBarScreen.Inicio.route){
            InicioProfessorScreen()
        }
        composable(route = BottomBarScreen.Chamado.route){
            val chamadosViewModel: ChamadosViewModel = koinViewModel()
            ChamadoProfessorScreen(navController, chamadosViewModel)
        }
        composable(route = BottomBarScreen.Relatorio.route){
            RelatorioProfessorScreen()
        }
        composable("login") {
            val loginViewModel: LoginViewModel = koinViewModel()
            LoginScreen(navController, loginViewModel)
        }
    }
}