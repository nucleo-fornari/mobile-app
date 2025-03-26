package com.example.nucleofornari.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.nucleofornari.screens.login.CodigoScreen
import com.example.nucleofornari.screens.login.EsqueceuSenhaScreen
import com.example.nucleofornari.screens.login.InicioLoginScreen
import com.example.nucleofornari.screens.login.LoginScreen
import com.example.nucleofornari.screens.login.RedefinirConfirmScreen
import com.example.nucleofornari.screens.login.RedefinirSenhaScreen
import com.example.nucleofornari.screens.login.SemContaScreen
import com.example.nucleofornari.screens.professor.AbrirChamadoScreen
import com.example.nucleofornari.screens.professor.CategoriasScreen
import com.example.nucleofornari.screens.professor.ChamadoEnviadoScreen
import com.example.nucleofornari.screens.professor.ChamadoProfessorScreen
import com.example.nucleofornari.screens.professor.InicioProfessorScreen
import com.example.nucleofornari.screens.professor.RelatorioProfessorScreen

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "auth") {

        navigation(startDestination = "inicio_login", route = "auth") {
            composable("inicio_login") { InicioLoginScreen(navController) }
            composable("login") { LoginScreen(navController) }
            composable("esqueceu_senha") { EsqueceuSenhaScreen(navController) }
            composable("codigo") { CodigoScreen(navController) }
            composable("redefinir_senha") { RedefinirSenhaScreen(navController) }
            composable("redefinir_confirm") { RedefinirConfirmScreen(navController) }
            composable("sem_conta") { SemContaScreen(navController) }
        }

        composable("main") {
            AppNavigation(navController)
        }
    }
}
