package com.example.nucleofornari.presentation.screen.professor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nucleofornari.presentation.common.component.Header
import com.example.nucleofornari.presentation.common.theme.NucleoFornariTheme
import com.example.nucleofornari.presentation.common.theme.Success
import com.example.nucleofornari.presentation.common.component.AppIcons
import com.example.nucleofornari.presentation.common.component.BlueButton
import com.example.nucleofornari.presentation.common.component.CardNucleo
import com.example.nucleofornari.presentation.navigation.BottomBarScreen

@Composable
fun ChamadoProfessorScreen( navController: NavController) {
    Scaffold(
        topBar = { Header("Meus chamados", onClick = {navController.navigate(BottomBarScreen.Inicio.route)}) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardNucleo("Junin cagou na sala",{ AppIcons.CheckCircle(Success)}, onclick = {})
            CardNucleo("Junin cagou na sala",{ AppIcons.CheckCircle(Success)}, onclick = {})
            CardNucleo("Junin cagou na sala",{ AppIcons.CheckCircle(Success)}, onclick = {})


            BlueButton("Abrir chamado", Color.White, onClick = {navController.navigate("abrir_chamado")})
        }
    }
}

@Preview (showBackground = true)
@Composable
fun ChamadoPreview() {
    NucleoFornariTheme{
        ChamadoProfessorScreen(navController = rememberNavController() )
    }
}