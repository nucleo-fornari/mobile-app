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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nucleofornari.data.model.chamado.ChamadoDto
import com.example.nucleofornari.presentation.common.component.Header
import com.example.nucleofornari.presentation.common.theme.Success
import com.example.nucleofornari.presentation.common.component.AppIcons
import com.example.nucleofornari.presentation.common.component.BlueButton
import com.example.nucleofornari.presentation.common.component.CardNucleo
import com.example.nucleofornari.presentation.common.theme.NucleoFornariTheme
import com.example.nucleofornari.presentation.common.theme.Warning
import com.example.nucleofornari.presentation.navigation.BottomBarScreen
import com.example.nucleofornari.util.UiState

@Composable
fun ChamadoProfessorScreen(
    navController: NavController,
    viewModel: ChamadosViewModel
) {

    val uiState by viewModel.listChamadosByIdUiState.collectAsState()
    var chamados by remember { mutableStateOf(emptyList<ChamadoDto>()) }

    LaunchedEffect(Unit) {
        viewModel.listChamadosById()
    }

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
            verticalArrangement = Arrangement.spacedBy(36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            when (uiState) {
                is UiState.Success<*> -> chamados = (uiState as UiState.Success<List<ChamadoDto>>).data

                is UiState.Loading -> {
                    CircularProgressIndicator()
                }

                is UiState.Error -> {
                    Text(
                        text = (uiState as UiState.Error).message,
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                is UiState.Empty -> {
                    Text(
                        text = "Nenhum chamado solicitado.",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(vertical = 24.dp)
            )
            {
                items(chamados.reversed()) { x ->
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


    }
}
