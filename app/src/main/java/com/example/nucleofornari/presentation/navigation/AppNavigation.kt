package com.example.nucleofornari.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nucleofornari.presentation.common.component.calendar.CalendarViewModel
import com.example.nucleofornari.presentation.screen.professor.AbrirChamadoScreen
import com.example.nucleofornari.presentation.screen.professor.CategoriasScreen
import com.example.nucleofornari.presentation.screen.professor.ChamadoEnviadoScreen
import com.example.nucleofornari.presentation.screen.professor.ChamadoProfessorScreen
import com.example.nucleofornari.presentation.screen.professor.InicioProfessorScreen
import com.example.nucleofornari.presentation.screen.professor.RelatorioProfessorScreen
import com.example.nucleofornari.presentation.common.theme.AzulPrincipal
import com.example.nucleofornari.presentation.screen.professor.CategoriasViewModel
import com.example.nucleofornari.presentation.screen.professor.ChamadosViewModel
import com.example.nucleofornari.presentation.screen.professor.InicioProfessorViewModel
import com.example.nucleofornari.presentation.screen.professor.RelatorioProfessorViewModel
import com.example.nucleofornari.presentation.screen.responsavel.MotivosScreen
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigation(navController: NavHostController){
    val navController = rememberNavController()
    Scaffold (
        modifier = Modifier.navigationBarsPadding(),
        bottomBar = {
            BottomBar(navController = navController) }
    ){
        NavHost(navController = navController, startDestination = BottomBarScreen.Inicio.route) {

            composable( route = BottomBarScreen.Inicio.route) {
                val calendarViewModel: CalendarViewModel = koinViewModel();
                val inicioViewModel: InicioProfessorViewModel = koinViewModel();
                InicioProfessorScreen(navController, inicioViewModel, calendarViewModel)
            }

            composable(route = BottomBarScreen.Chamado.route) {
                val chamadosViewModel: ChamadosViewModel = koinViewModel();
                ChamadoProfessorScreen(navController, chamadosViewModel)
            }

            composable(route = BottomBarScreen.Relatorio.route) {
                val relatorioViewModel: RelatorioProfessorViewModel = koinViewModel()
                RelatorioProfessorScreen(navController, relatorioViewModel)
            }

            composable("abrir_chamado") {
                val chamadosViewModel: ChamadosViewModel = koinViewModel()
                AbrirChamadoScreen(navController, chamadosViewModel) }

            composable("selecionar_categoria") {
                val categoriaViewModel: CategoriasViewModel = koinViewModel()
                CategoriasScreen(navController, categoriaViewModel) }
            composable("selecionar_motivo") {

                MotivosScreen() }
            composable("chamado_enviado") { ChamadoEnviadoScreen(navController) }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController){
    val screens = listOf(
        BottomBarScreen.Inicio,
        BottomBarScreen.Chamado,
        BottomBarScreen.Relatorio
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
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
){
    BottomNavigationItem(
        label = {
            Text(text = screen.title, color = Color.White)
        },
        icon = {
            Icon(imageVector = screen.icon, contentDescription = "Navigation Icon", tint = Color.White)
        },
        selected = currentDestination?.hierarchy?.any{
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

@Composable
@Preview
fun AppNavigationPreview(){
    AppNavigation(navController = rememberNavController())
}