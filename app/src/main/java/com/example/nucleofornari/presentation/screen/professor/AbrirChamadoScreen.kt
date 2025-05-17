package com.example.nucleofornari.presentation.screen.professor

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import com.example.nucleofornari.presentation.common.component.Header
import com.example.nucleofornari.presentation.common.theme.PretoPrincipal
import com.example.nucleofornari.presentation.common.component.AppIcons
import com.example.nucleofornari.presentation.common.component.BlueButton
import com.example.nucleofornari.presentation.common.component.CardNucleo
import com.example.nucleofornari.presentation.common.component.NucleoLongTextField
import com.example.nucleofornari.presentation.common.component.NucleoSwitch
import com.example.nucleofornari.presentation.navigation.BottomBarScreen
import androidx.compose.runtime.remember
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.nucleofornari.data.model.chamado.ChamadoDto
import com.example.nucleofornari.data.model.chamado.TipoChamadoDto
import com.example.nucleofornari.util.UiState

@Composable
fun AbrirChamadoScreen(
    navController: NavController,
    viewModel: ChamadosViewModel,
) {

    val uiState by viewModel.createChamadoUiState.collectAsState()
    val navBackStackEntry = remember { navController.currentBackStackEntry }
    val savedStateHandle = navBackStackEntry?.savedStateHandle
    val categoriaSelecionada = savedStateHandle?.getLiveData<TipoChamadoDto>("categoriaSelecionada")?.observeAsState()
    val descricao = remember { mutableStateOf("") }
    val criancaAtipica = remember { mutableStateOf(false) }
    var tipoChamado by remember { mutableStateOf(TipoChamadoDto()) }

    LaunchedEffect(uiState) {
        if (uiState is UiState.Success<*>) {
            navController.navigate("chamado_enviado")
            viewModel.resetCreateChamadoUiState()
        }
    }

    LaunchedEffect(categoriaSelecionada?.value) {
        categoriaSelecionada?.value?.let {
            Log.d("AbrirChamado", "Categoria selecionada: $it")
            tipoChamado = it;
            savedStateHandle.remove<String>("categoriaSelecionada")
        }
    }

    Scaffold(
        topBar = {
            Header(
                "Abrir chamado",
                onClick = { navController.navigate(BottomBarScreen.Chamado.route) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardNucleo(
                "Selecione a categoria",
                { AppIcons.ArrowForward(PretoPrincipal) },
                onclick = { navController.navigate("selecionar_categoria") }
            )

            if (tipoChamado.id != null) {
                Text(
                    text = "Categoria selecionada: ${tipoChamado.tipo}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            NucleoLongTextField("O que está acontecendo?", descricao.value) {
                descricao.value = it
            }

            Row(
                modifier = Modifier.width(300.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Criança atípica ou em observação?")
                NucleoSwitch(criancaAtipica.value) { criancaAtipica.value = it }
            }

            BlueButton("Concluir", Color.White, {
                val ch = ChamadoDto()
                ch.tipo = tipoChamado
                ch.criancaAtipica = criancaAtipica.value
                ch.descricao = descricao.value
                viewModel.createChamado(ch)
            })

            when (uiState) {
                is UiState.Error -> Text(
                    text = (uiState as UiState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 16.dp)
                )
                UiState.Loading -> {
                    CircularProgressIndicator()
                }
                is UiState.Success<*> -> navController.navigate("chamado_enviado")
                else -> {}
            }
        }
    }
}


//@Preview
//@Composable
//fun AbrirChamadoScreenPreview() {
//    AbrirChamadoScreen(navController = rememberNavController())
//}