package com.example.nucleofornari.screens.professor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nucleofornari.ui.theme.AzulPrincipal
import com.example.nucleofornari.ui.theme.Success
import com.example.nucleofornari.ui.theme.components.AppIcons
import com.example.nucleofornari.ui.theme.components.CardNucleo

@Composable
fun InicioProfessorScreen(navController: NavController){
    Scaffold(
        topBar = {
            Row(modifier = Modifier.padding(30.dp)) {
                AppIcons.Menu(AzulPrincipal)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                Text(text = "Eventos", fontSize = 20.sp, color = Color.Gray)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Alunos", fontSize = 20.sp, color = AzulPrincipal)
            }

            Spacer(modifier = Modifier.height(20.dp))
            val nomes = listOf(
                "Gustavo Henrique", "Ana Beatriz", "Carlos Eduardo", "Maria Clara", "José Silva",
                "Luiza Souza", "Fernando Lima", "Paula Costa", "Ricardo Martins", "Lúcia Rocha",
                "Carlos Alberto", "Vanessa Pereira", "Rafael Almeida", "Mariana Gomes", "Juliana Oliveira",
                "Leonardo Santos", "Camila Freitas", "Felipe Ferreira", "Juliano Rocha", "Aline Santos",
                "Roberta Lima", "Vitor Costa", "Lucas Souza", "Beatriz Alves", "João Pedro",
                "Tânia Lima", "Eduardo Pereira", "Patrícia Gonçalves", "Marcelo Silva", "Cristina Martins"
            )

            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                items(nomes) { nome ->
                    CardNucleo(nome, { AppIcons.CheckCircle(Success) }, onclick = {})
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InicioPreview() {
    com.example.nucleofornari.ui.theme.NucleoFornariTheme{
        InicioProfessorScreen(navController = rememberNavController() )
    }
}
