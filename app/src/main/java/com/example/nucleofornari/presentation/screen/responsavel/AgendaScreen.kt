package com.example.nucleofornari.presentation.screen.responsavel

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenu
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nucleofornari.presentation.common.theme.AppTypography
import com.example.nucleofornari.presentation.common.theme.AzulPrincipal
import com.example.nucleofornari.presentation.common.theme.NucleoFornariTheme
import com.example.nucleofornari.presentation.common.component.AppIcons
import com.example.nucleofornari.presentation.common.component.CardNucleoLarge
import com.example.nucleofornari.presentation.common.component.Header
import com.example.nucleofornari.presentation.common.component.MenuLateral
import com.example.nucleofornari.util.UiState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AgendaScreen(navController: NavHostController, viewModel: AgendaViewModel = getViewModel()) {
    val uiStateAfiliados by viewModel.uiStateAfiliados
    val alunoSelecionado = viewModel.alunoSelecionado
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var expanded by remember { mutableStateOf(false) }


    MenuLateral(drawerState = drawerState,
        navController = navController, content = {
            Scaffold(
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

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(top = 24.dp)
                                    .clickable { expanded = true }
                            ) {
                                Text(
                                    text = "Agenda de ",
                                    style = AppTypography.headlineLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = AzulPrincipal
                                )

                                Box {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.clickable { expanded = true }
                                    ) {
                                        Text(
                                            text = alunoSelecionado?.nome ?: "Selecione um aluno",
                                            style = AppTypography.headlineLarge,
                                            fontWeight = FontWeight.Bold,
                                            color = AzulPrincipal
                                        )
                                        Icon(
                                            Icons.Default.ArrowDropDown,
                                            contentDescription = "Selecione um aluno",
                                            tint = AzulPrincipal
                                        )
                                    }

                                    DropdownMenu(
                                        expanded = expanded,
                                        onDismissRequest = { expanded = false }
                                    ) {
                                        afiliados.forEach { aluno ->
                                            DropdownMenuItem(
                                                text = { Text(aluno.nome) },
                                                onClick = {
                                                    viewModel.selecionarAluno(aluno)
                                                    expanded = false
                                                }
                                            )
                                        }
                                    }
                                }
                            }

                            alunoSelecionado?.let {
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding)
                                        .padding(bottom = 24.dp),
                                    verticalArrangement = Arrangement.spacedBy(24.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    items(it.recados) { recado ->
                                        CardNucleoLarge(
                                            recado.titulo,
                                            recado.conteudo,
                                            formatarData(recado.dtCriacao),
                                            recado.responsavel.nome
                                        )
                                    }
                                    item {
                                        androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(32.dp))
                                    }
                                }
                            }
                        }

                        is UiState.Empty -> {
                            Text(text = "Nenhum afiliado encontrado.")
                        }
                    }
                }
            }
        })

}


// Função para formatar a data
fun formatarData(dataIso: String?): String {
    return try {
        if (dataIso == null) return "Data não disponível"
        val localDateTime = LocalDateTime.parse(dataIso) // Faz o parse para LocalDateTime
        localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) // Formata a data
    } catch (e: Exception) {
        "Data inválida" // Caso a data não seja válida
    }
}


//@Composable
//@Preview(showBackground = true)
//fun AgendaPreview() {
//    NucleoFornariTheme {
//        AgendaScreen(navController = rememberNavController())
//    }
//}