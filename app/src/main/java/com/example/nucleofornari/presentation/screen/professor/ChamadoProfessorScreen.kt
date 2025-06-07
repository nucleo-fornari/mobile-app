package com.example.nucleofornari.presentation.screen.professor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.nucleofornari.presentation.common.component.NucleoLoading
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
fun ChamadoProfessorScreen(
    navController: NavController,
    viewModel: ChamadosViewModel = getViewModel()
) {

    val uiState = viewModel.uiStateChamados

    Scaffold(
        topBar = { Header("Meus chamados", onClick = {navController.navigate(BottomBarScreen.Inicio.route)}) }
    ,
    bottomBar = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(bottom = 54.dp),
            contentAlignment = Alignment.BottomCenter

        ) {
            BlueButton("Abrir chamado", Color.White, onClick = {navController.navigate("abrir_chamado")})
        }
    }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (uiState) {
                is UiState.Loading -> {
                    NucleoLoading()
                }

                is UiState.Error -> {
                    Text(
                        text = uiState.message,
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                is UiState.Success -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        contentPadding = PaddingValues(bottom = 16.dp)
                    ){
                        items(uiState.data.reversed()) { x ->
                            CardNucleo(
                                x.descricao,
                                {
                                    if (x.finalizado == true) {
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
                    Text(
                        text = "Nenhum chamado solicitado.",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }


        }


    }
