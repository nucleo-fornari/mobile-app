package com.example.nucleofornari.presentation.screen.professor

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nucleofornari.presentation.common.theme.AzulPrincipal
import com.example.nucleofornari.presentation.common.theme.NucleoFornariTheme
import com.example.nucleofornari.presentation.common.theme.Success
import com.example.nucleofornari.presentation.common.component.AppIcons
import com.example.nucleofornari.presentation.common.component.Calendar
import com.example.nucleofornari.presentation.common.component.CardNucleo
import com.example.nucleofornari.presentation.common.component.Header
import com.example.nucleofornari.presentation.common.component.MenuLateral
import kotlinx.coroutines.launch

@Composable
fun InicioProfessorScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf("Eventos") } // Controla a exibição
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    MenuLateral(drawerState = drawerState,
        navController = navController, content = {
        Scaffold(
            topBar = {
                Header(
                    "",
                    bgcolor = Color.Transparent,
                    navIcon = Icons.Filled.Menu,
                    iconColor = AzulPrincipal,
                    onClick = { scope.launch { drawerState.open() } })
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Text(
                        text = "Eventos",
                        fontSize = 20.sp,
                        color = if (selectedTab == "Eventos") AzulPrincipal else Color.Gray,
                        modifier = Modifier.clickable { selectedTab = "Eventos" }
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Alunos",
                        fontSize = 20.sp,
                        color = if (selectedTab == "Alunos") AzulPrincipal else Color.Gray,
                        modifier = Modifier.clickable { selectedTab = "Alunos" }
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))

                if (selectedTab == "Alunos") {
                    ListaDeAlunos()
                } else {
                    Calendar()
                }
            }
        }
    })
}

    @Composable
    fun ListaDeAlunos() {
        val nomes = listOf(
            "Gustavo Henrique",
            "Ana Beatriz",
            "Carlos Eduardo",
            "Maria Clara",
            "José Silva",
            "Luiza Souza",
            "Fernando Lima",
            "Paula Costa",
            "Ricardo Martins",
            "Lúcia Rocha",
            "Carlos Alberto",
            "Vanessa Pereira",
            "Rafael Almeida",
            "Mariana Gomes",
            "Juliana Oliveira",
            "Leonardo Santos",
            "Camila Freitas",
            "Felipe Ferreira",
            "Juliano Rocha",
            "Aline Santos",
            "Roberta Lima",
            "Vitor Costa",
            "Lucas Souza",
            "Beatriz Alves",
            "João Pedro",
            "Tânia Lima",
            "Eduardo Pereira",
            "Patrícia Gonçalves",
            "Marcelo Silva",
            "Cristina Martins"
        )

        LazyColumn(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.spacedBy(4.dp),) {
            items(nomes) { nome ->
                CardNucleo(nome, { AppIcons.CheckCircle(Success) }, onclick = {})
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun InicioPreview() {
    NucleoFornariTheme {
        InicioProfessorScreen(navController = rememberNavController())
    }
}
