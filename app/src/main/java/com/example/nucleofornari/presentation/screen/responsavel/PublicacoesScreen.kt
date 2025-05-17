package com.example.nucleofornari.presentation.screen.responsavel

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nucleofornari.data.model.evento.EventoDto
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


@Composable
fun PublicacoesScreen(
    navController: NavHostController,
    viewModel: PublicacoesViewModel = getViewModel()
) {
    val state = viewModel.uiStateEventos
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

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
                    Text(
                        text = "Publicações",
                        style = AppTypography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = AzulPrincipal,
                        modifier = Modifier.padding(vertical = 20.dp)
                    )

                    when (state) {
                        is UiState.Loading -> {
                            CircularProgressIndicator()
                        }

                        is UiState.Error -> {
                            Text(text = state.message, color = Color.Red)
                        }

                        is UiState.Success -> {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(innerPadding)
                                    .padding(bottom = 24.dp),
                                verticalArrangement = Arrangement.spacedBy(24.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                items(state.data) { publicacao ->
                                    CardNucleoLarge(
                                        publicacao.titulo,
                                        publicacao.descricao,
                                        formatarData(publicacao.data),
                                        publicacao.responsavel.nome,
                                    )
                                }
                                item {
                                    androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(32.dp))
                                }
                            }
                        }

                        is UiState.Empty -> {
                            Text("Nenhuma publicação encontrada.")
                        }
                    }
                }
            }
        })


}


@Composable
@Preview(showBackground = true)
fun PublicacoesScreenPreview() {
    NucleoFornariTheme {
        PublicacoesScreen(navController = rememberNavController())
    }
}