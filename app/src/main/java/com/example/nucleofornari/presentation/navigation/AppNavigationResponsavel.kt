package com.example.nucleofornari.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nucleofornari.presentation.common.theme.AzulPrincipal
import com.example.nucleofornari.presentation.screen.responsavel.agenda.AgendaScreen
import com.example.nucleofornari.presentation.screen.responsavel.publicacoes.PublicacoesScreen
import com.example.nucleofornari.presentation.screen.responsavel.reunioes.ReunioesScreen
import com.example.nucleofornari.presentation.screen.responsavel.reunioes_solicitadas.ReunioesSolicitasScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigationResponsavel(navController: NavHostController){
    val navController = rememberNavController()
    Scaffold (
        modifier = Modifier.navigationBarsPadding(),
        bottomBar = {
             BottomBarResponsavel(navController = navController) }
    ){
        NavHost(navController = navController, startDestination = BottomBarScreen.Agenda.route) {
            composable(route = BottomBarScreen.Agenda.route) { AgendaScreen(navController) }
            composable(route = BottomBarScreen.Publicacao.route) { PublicacoesScreen(navController) }
            composable(route = BottomBarScreen.Reuniao.route) { ReunioesSolicitasScreen(navController) }
            composable(route = "solicitar_reuniao") { ReunioesScreen(navController) }
        }
    }
}

@Composable
fun BottomBarResponsavel(navController: NavHostController){
    val screens = listOf(
        BottomBarScreen.Agenda,
        BottomBarScreen.Publicacao,
        BottomBarScreen.Reuniao
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = AzulPrincipal
            ) {
        screens.forEach { screen ->
            AddItem(screen = screen, currentDestination = currentDestination, navController = navController)
        }
    }
}

@Composable
@Preview
fun AppNavigationResponsavelPreview(){
    AppNavigationResponsavel(navController = rememberNavController())
}