package com.example.nucleofornari.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.nucleofornari.presentation.screen.auth.CodigoScreen
import com.example.nucleofornari.presentation.screen.auth.EsqueceuSenhaScreen
import com.example.nucleofornari.presentation.screen.auth.InicioLoginScreen
import com.example.nucleofornari.presentation.screen.auth.login.LoginScreen
import com.example.nucleofornari.presentation.screen.auth.RedefinirConfirmScreen
import com.example.nucleofornari.presentation.screen.auth.RedefinirSenhaScreen
import com.example.nucleofornari.presentation.screen.auth.SemContaScreen

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
