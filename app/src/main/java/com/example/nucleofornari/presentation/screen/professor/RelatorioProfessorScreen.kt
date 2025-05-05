package com.example.nucleofornari.presentation.screen.professor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nucleofornari.presentation.common.component.CardOutlinedNucleo
import com.example.nucleofornari.presentation.common.component.Header
import com.example.nucleofornari.presentation.navigation.BottomBarScreen
import okhttp3.internal.http2.Header

@Composable
fun RelatorioProfessorScreen(navController: NavController){
    Scaffold(
        topBar = { Header("Meus relatórios", onClick = {navController.navigate(BottomBarScreen.Inicio.route)}) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ListaDeAlunosComData()
        }
    }
}


@Composable
fun ListaDeAlunosComData() {
    val alunos = listOf(
        "Julia Damacena" to "21/10/2023",
        "Carlos Silva" to "15/10/2023",
        "Ana Pereira" to "10/10/2023",
        "Marcos Souza" to "05/10/2023",
        "Beatriz Lima" to "01/10/2023",
        "Julia Damacena" to "21/10/2023",
        "Carlos Silva" to "15/10/2023",
        "Ana Pereira" to "10/10/2023",
        "Marcos Souza" to "05/10/2023",
        "Beatriz Lima" to "01/10/2023",
        "Julia Damacena" to "21/10/2023",
        "Carlos Silva" to "15/10/2023",
        "Ana Pereira" to "10/10/2023",
        "Marcos Souza" to "05/10/2023",
        "Beatriz Lima" to "01/10/2023",
        "Julia Damacena" to "21/10/2023",
        "Carlos Silva" to "15/10/2023",
        "Ana Pereira" to "10/10/2023",
        "Marcos Souza" to "05/10/2023",
        "Beatriz Lima" to "01/10/2023",
        "Julia Damacena" to "21/10/2023",
        "Carlos Silva" to "15/10/2023",
        "Ana Pereira" to "10/10/2023",
        "Marcos Souza" to "05/10/2023",
        "Beatriz Lima" to "01/10/2023"

    )

    LazyColumn(modifier = Modifier.fillMaxHeight(),verticalArrangement = Arrangement.spacedBy(4.dp),) {
        items(alunos) { (nome, data) ->
            CardOutlinedNucleo(nome, data)
        }
    }
}
