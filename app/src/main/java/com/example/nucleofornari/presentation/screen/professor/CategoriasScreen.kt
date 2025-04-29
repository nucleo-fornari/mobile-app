package com.example.nucleofornari.presentation.screen.professor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
    var selectedCategoriaIndex by remember { mutableIntStateOf(-1) }

    Scaffold(
        topBar = {
            Header("Categorias", onClick = { navController.navigate("abrir_chamado") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (res) {
                is UiState.Loading -> {
                    CircularProgressIndicator()
                }

                is UiState.Success -> {
                    val categorias = res.data
                    NucleoRadioButtonList(
                        categorias.map { it.tipo },
                        selectedOptionIndex = selectedCategoriaIndex,
                        onOptionSelected = { selectedCategoriaIndex = it }
                    )

                    Spacer(modifier = Modifier.height(100.dp))

                    BlueButton(
                        "Confirmar",
                        Color.White,
                        onClick = {
                            val categoriaSelecionada =
                                if (selectedCategoriaIndex > -1) categorias[selectedCategoriaIndex]
                                else TipoChamadoDto()

                            navController.previousBackStackEntry
                                ?.savedStateHandle
                                ?.set("categoriaSelecionada", categoriaSelecionada)

                            navController.popBackStack()
                        }
                    )
                }

                is UiState.Error -> {
                    Text(
                        text = res.message,
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                UiState.Empty -> {
                    Text(
                        text = "Nenhuma categoria carregada.",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}



//@Preview
//@Composable
//fun CategoriaScreenPreview() {
//    CategoriasScreen(navController = rememberNavController())
//}