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
import com.example.nucleofornari.presentation.screen.auth.login.LoginViewModel
import com.example.nucleofornari.presentation.screen.auth.login.RecuperacaoSenhaViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "auth") {

        navigation(startDestination = "inicio_login", route = "auth") {
            composable("inicio_login") { InicioLoginScreen(navController) }

            composable("login") {
                val loginViewModel: LoginViewModel = koinViewModel()
                LoginScreen(navController, loginViewModel)
            }
            composable("esqueceu_senha") {
                val recuperacaoSenhaViewModel: RecuperacaoSenhaViewModel = koinViewModel()
                EsqueceuSenhaScreen(navController, recuperacaoSenhaViewModel)
            }
            composable("codigo") {
                val email = navController
                    .previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<String>("email")
                val recuperacaoSenhaViewModel: RecuperacaoSenhaViewModel = koinViewModel()
                CodigoScreen(navController, email, recuperacaoSenhaViewModel) }
            composable("redefinir_senha") {
                val email = navController
                    .previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<String>("email")
                val codigo = navController
                    .previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<String>("codigo")

                val recuperacaoSenhaViewModel: RecuperacaoSenhaViewModel = koinViewModel()
                RedefinirSenhaScreen(navController, email, codigo, recuperacaoSenhaViewModel) }
            composable("redefinir_confirm") { RedefinirConfirmScreen(navController) }
            composable("sem_conta") { SemContaScreen(navController) }
        }

        composable("main") {
            AppNavigation(navController)
        }

        composable("responsavel") {
            AppNavigationResponsavel(navController)
        }
    }
}
