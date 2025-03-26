package com.example.nucleofornari.screens.professor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.example.nucleofornari.ui.theme.components.BlueButton
import com.example.nucleofornari.ui.theme.components.NucleoRadioButtonList

@Composable
fun CategoriasScreen(navController: NavController) {
    val categorias = listOf(
        "Professora precisa sair da sala",
        "Saúde da criança",
        "Suporte da secretaria",
        "Suporte de TI",
        "Problema de infraestrutura",
        "Outros"
    )
    Scaffold(
        topBar = { com.example.nucleofornari.ui.theme.components.Header("Categorias") }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            NucleoRadioButtonList(categorias)
            Spacer(modifier = Modifier.padding(100.dp))

            Row(modifier = Modifier
                .padding(bottom= 32.dp)){
                BlueButton("Confirmar", Color.White, onClick = {navController.navigate("abrir_chamado")})
            }

        }
    }
}

@Preview
@Composable
fun CategoriaScreenPreview() {
    CategoriasScreen(navController = rememberNavController())
}