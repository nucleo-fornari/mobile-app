package com.example.nucleofornari.presentation.screen.professor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nucleofornari.data.model.aluno.AlunoResponseDto
import com.example.nucleofornari.presentation.common.component.CardOutlinedNucleo
import com.example.nucleofornari.presentation.common.component.Header
import com.example.nucleofornari.presentation.navigation.BottomBarScreen
import com.example.nucleofornari.util.UiState
import org.koin.androidx.compose.getViewModel

@Composable
fun RelatorioProfessorScreen(navController: NavController, viewModel: RelatorioProfessorViewModel = getViewModel()){

    val state = viewModel.uiStateAlunosComRelatorio

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
            ListaDeAlunosComData(state, viewModel)
        }
    }
}


@Composable
fun ListaDeAlunosComData(state: UiState<List<AlunoResponseDto>>, viewModel: RelatorioProfessorViewModel) {
    val context = LocalContext.current
    when (state) {
        is UiState.Loading -> {
            CircularProgressIndicator()
        }
        is UiState.Success -> {
            val alunosComData = state.data.flatMap { aluno ->
                aluno.avaliacoes.map { avaliacao ->
                    aluno.nome to avaliacao
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(alunosComData) { (nome, avaliacao) ->
                    CardOutlinedNucleo(nome, avaliacao.dtCriacao, onclick = { viewModel.downloadAvaliacaoPdf(avaliacao.id, context) })
                }
            }
        }
        is UiState.Empty -> {
            Text(text = "Nenhum aluno encontrado com avaliações.")
        }
        is UiState.Error -> {
            Text(text = state.message ?: "Erro desconhecido")
        }
    }
}
