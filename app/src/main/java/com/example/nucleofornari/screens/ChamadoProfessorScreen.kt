package com.example.nucleofornari.screens

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
import com.example.nucleofornari.ui.theme.Success
import com.example.nucleofornari.ui.theme.components.AppIcons
import com.example.nucleofornari.ui.theme.components.BlueButton
import com.example.nucleofornari.ui.theme.components.CardNucleo

@Composable
fun ChamadoProfessorScreen( navController: NavController) {
    Scaffold(
        topBar = { com.example.nucleofornari.ui.theme.components.Header("Meus chamados") }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardNucleo("Junin cagou na sala",{AppIcons.CheckCircle(Success)}, onclick = {})
            CardNucleo("Junin cagou na sala",{AppIcons.CheckCircle(Success)}, onclick = {})
            CardNucleo("Junin cagou na sala",{AppIcons.CheckCircle(Success)}, onclick = {})


            BlueButton("Abrir chamado", Color.White, onClick = {navController.navigate("abrir_chamado")})
        }
    }
}

@Preview (showBackground = true)
@Composable
fun ChamadoPreview() {
    com.example.nucleofornari.ui.theme.NucleoFornariTheme{
        ChamadoProfessorScreen(navController = rememberNavController() )
    }
}