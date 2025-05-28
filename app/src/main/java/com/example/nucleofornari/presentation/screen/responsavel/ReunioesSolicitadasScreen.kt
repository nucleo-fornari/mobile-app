package com.example.nucleofornari.presentation.screen.responsavel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nucleofornari.data.model.agendamento.AgendamentoDto
import com.example.nucleofornari.data.model.chamado.ChamadoDto
import com.example.nucleofornari.presentation.common.component.Header
import com.example.nucleofornari.presentation.common.theme.Success
import com.example.nucleofornari.presentation.common.component.AppIcons
import com.example.nucleofornari.presentation.common.component.BlueButton
import com.example.nucleofornari.presentation.common.component.CardNucleo
import com.example.nucleofornari.presentation.common.theme.Warning
import com.example.nucleofornari.presentation.navigation.BottomBarScreen
import com.example.nucleofornari.util.UiState
import org.koin.androidx.compose.getViewModel

@Composable
fun ReunioesSolicitasScreen(
    navController: NavController,
    viewModel: ReunioesSolicitadasViewModel = getViewModel()
) {

    val state = viewModel.uiStateReunioes

    Scaffold(
        topBar = { Header("Minhas solicitações", onClick = {navController.navigate(BottomBarScreen.Agenda.route)}) },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .padding(bottom = 54.dp),
                contentAlignment = Alignment.BottomCenter

            ) {
                BlueButton("Solicitar reunião", Color.White, onClick = {navController.navigate("solicitar_reuniao")})
            }
        }
    ) { innerPadding ->

            when (state){
                is UiState.Loading -> {
                    CircularProgressIndicator()
                }

                is UiState.Error -> {
                    Text(text = state.message, color = Color.Red)
                }

                is UiState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        contentPadding = PaddingValues(vertical = 24.dp)
                    ){
                        items(state.data.reversed()) { x ->
                            CardNucleo(
                                x.descricao,
                                {
                                    if (x.aceito == true) {
                                        AppIcons.CheckCircle(Success)
                                    } else {
                                        AppIcons.CheckCircle(Warning)
                                    }
                                },
                                onclick = {}
                            )
                        }
                    }

                }

                is UiState.Empty -> {
                    Text("Nenhuma reunião solicitada.")
                }


            }
    }
}
