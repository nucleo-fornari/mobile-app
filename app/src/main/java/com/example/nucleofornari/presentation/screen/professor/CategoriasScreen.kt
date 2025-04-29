package com.example.nucleofornari.presentation.screen.professor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nucleofornari.data.model.chamado.TipoChamadoDto
import com.example.nucleofornari.presentation.common.component.Header
import com.example.nucleofornari.presentation.common.component.BlueButton
import com.example.nucleofornari.presentation.common.component.NucleoRadioButtonList
import com.example.nucleofornari.util.UiState

@Composable
fun CategoriasScreen(
    navController: NavController,
    viewModel: CategoriasViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.findCategorias()
    }

    val res = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = { Header("Categorias", onClick = { navController.navigate("abrir_chamado") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (res) {
                is UiState.Loading -> {
                    CircularProgressIndicator()
                }

                is UiState.Success -> {
                    val categorias = res.data
                    NucleoRadioButtonList(categorias.map { it.tipo })
                    Spacer(modifier = Modifier.padding(100.dp))

                    Row(modifier = Modifier.padding(bottom = 32.dp)) {
                        BlueButton(
                            "Confirmar",
                            Color.White,
                            onClick = { navController.navigate("abrir_chamado") }
                        )
                    }
                }

                else -> {}
            }
        }
    }
}


//@Preview
//@Composable
//fun CategoriaScreenPreview() {
//    CategoriasScreen(navController = rememberNavController())
//}