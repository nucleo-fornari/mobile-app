package com.example.nucleofornari.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nucleofornari.ui.theme.PretoPrincipal
import com.example.nucleofornari.ui.theme.components.AppIcons
import com.example.nucleofornari.ui.theme.components.BlueButton
import com.example.nucleofornari.ui.theme.components.CardNucleo
import com.example.nucleofornari.ui.theme.components.NucleoLongTextField
import com.example.nucleofornari.ui.theme.components.NucleoSwitch

@Composable
fun AbrirChamadoScreen(navController: NavController) {
    Scaffold(
        topBar = { com.example.nucleofornari.ui.theme.components.Header("Abrir chamado") }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardNucleo("Selecione a categoria", { AppIcons.ArrowForward(PretoPrincipal) }, onclick = {navController.navigate("selecionar_categoria")})
            NucleoLongTextField("O que está acontecendo?")

            Row(
                modifier = Modifier
                    .width(300.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Criança atípica ou em observação?"
                )
                NucleoSwitch()
            }

            BlueButton("Concluir", Color.White, onClick = {navController.navigate("chamado")})
        }
    }
}


@Preview
@Composable
fun AbrirChamadoScreenPreview() {
    AbrirChamadoScreen(navController = rememberNavController())
}