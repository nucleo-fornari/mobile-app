package com.example.nucleofornari.presentation.screen.responsavel

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nucleofornari.presentation.common.component.BlueButton
import com.example.nucleofornari.presentation.common.component.DatePickerFieldToModalNucleo
import com.example.nucleofornari.presentation.common.component.Header
import com.example.nucleofornari.presentation.common.component.MenuLateral
import com.example.nucleofornari.presentation.common.component.NucleoLongTextField
import com.example.nucleofornari.presentation.common.theme.AzulPrincipal
import com.example.nucleofornari.presentation.common.theme.NucleoFornariTheme
import com.example.nucleofornari.util.UiState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun ReunioesScreen(navController: NavController, viewModel: ReunioesViewModel = getViewModel()) {

    val uiStateAfiliados by viewModel.uiStateAfiliados
    val alunoSelecionado = viewModel.alunoSelecionado
    val categorias = listOf("Administrativo", "Documentação", "Denúncia")
    val categoriaSelecionada = viewModel.categoriaSelecionada
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var categoriaExpanded by remember { mutableStateOf(false) }
    var alunoExpanded by remember { mutableStateOf(false) }

    val descricao = viewModel.descricao

    var dataSelecionada by remember { mutableStateOf<Long?>(null) }

    MenuLateral(drawerState = drawerState,
        navController = navController, content = {
            androidx.compose.material.Scaffold(
                topBar = {
                    Header(
                        "",
                        bgcolor = Color.Transparent,
                        navIcon = Icons.Filled.Menu,
                        iconColor = AzulPrincipal,
                        onClick = { scope.launch { drawerState.open() } }
                    )
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (val state = uiStateAfiliados) {
                        is UiState.Loading -> {
                            CircularProgressIndicator()
                        }

                        is UiState.Error -> {
                            Text(text = state.message, color = Color.Red)
                        }

                        is UiState.Success -> {
                            val afiliados = state.data


//                            CardNucleo(
//                                "Selecione a categoria",
//                                { AppIcons.ArrowForward(PretoPrincipal) },
//                                onclick = { }
//                            )

                            ElevatedCard(
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 6.dp
                                ),
                                modifier = Modifier
                                    .size(width = 300.dp, height = 60.dp)
                                    .clickable { categoriaExpanded = true }

                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = categoriaSelecionada ?: "Selecione a categoria",
                                    )
                                    Icon(
                                        Icons.Default.ArrowDropDown,
                                        contentDescription = "Selecione a categoria",
                                    )
                                }

                                DropdownMenu(
                                    expanded = categoriaExpanded,
                                    onDismissRequest = { categoriaExpanded = false }
                                ) {
                                    categorias.forEach { categoria ->
                                        DropdownMenuItem(
                                            text = { Text(categoria) },
                                            onClick = {
                                                viewModel.selecionarCategoria(categoria)
                                                categoriaExpanded = false
                                            }
                                        )
                                    }
                                }
                            }

                            ElevatedCard(
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 6.dp
                                ),
                                modifier = Modifier
                                    .size(width = 300.dp, height = 60.dp)
                                    .clickable { alunoExpanded = true }

                            ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(20.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = alunoSelecionado?.nome ?: "Selecione um aluno",
                                            textAlign = TextAlign.Center
                                        )
                                        Icon(
                                            Icons.Default.ArrowDropDown,
                                            contentDescription = "Selecione um aluno",
                                        )
                                    }

                                    DropdownMenu(
                                        expanded = alunoExpanded,
                                        onDismissRequest = { alunoExpanded = false }
                                    ) {
                                        afiliados.forEach { aluno ->
                                            DropdownMenuItem(
                                                text = { Text(aluno.nome) },
                                                onClick = {
                                                    viewModel.selecionarAluno(aluno)
                                                    alunoExpanded = false
                                                }
                                            )
                                        }
                                    }
                            }
                            NucleoLongTextField(
                                "Descreva o motivo da reunião",
                                value = descricao,
                                valueChangeHandler = {viewModel.atualizarDescricao(it)}
                            )

                            Row(
                                modifier = Modifier
                                    .width(300.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                DatePickerFieldToModalNucleo(
                                    selectedDateMillis = dataSelecionada,
                                    onDateSelected = { novaData ->
                                        dataSelecionada = novaData
                                        viewModel.atualizarData(novaData)
                                    }
                                )
                            }

                            BlueButton(
                                "Enviar",
                                Color.White,
                                onClick = { if (alunoSelecionado != null && dataSelecionada != null) {
                                    viewModel.criarAgendamento(
                                        salaId = alunoSelecionado!!.sala?.id ?: 0,
                                        motivo = categoriaSelecionada ?: "",
                                        descricao = descricao,
                                        dataEmMillis = dataSelecionada!!,
                                        onSuccess = {navController.navigate("reunioes") },
                                        onError = { erro -> Log.e("API", "Erro: ${erro.message}") }
                                    )
                                } else {
                                    // mostrar erro de campos obrigatórios
                                }})
                        }

                        is UiState.Empty -> {
                            Text(text = "Nenhum afiliado encontrado.")
                        }


                    }
                }
            }
        })
}

@Composable
@Preview(showBackground = true)
fun ReunioesScreenPreview() {
    NucleoFornariTheme {
        ReunioesScreen(navController = rememberNavController())
    }
}