package com.example.nucleofornari.presentation.screen.responsavel

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nucleofornari.presentation.common.theme.AppTypography
import com.example.nucleofornari.presentation.common.theme.AzulPrincipal
import com.example.nucleofornari.presentation.common.theme.NucleoFornariTheme
import com.example.nucleofornari.presentation.common.component.AppIcons
import com.example.nucleofornari.presentation.common.component.CardNucleoLarge

//@Serializable
data class Publicacao(
    val id: Int,
    val titulo: String,
    val descricao: String,
    val autor: String,
    val data: String,
)

@Composable
fun listaDePublicacoes() {
    val publicacoes = listOf(
        Publicacao(1, "Recado 1", "Descrição do recado 1", "Professor A", "01/01/2023"),
        Publicacao(2, "Recado 2", "Descrição do recado 2", "Professor B", "02/01/2023"),
        Publicacao(3, "Recado 3", "Descrição do recado 3", "Professor C", "03/01/2023"),
        Publicacao(4, "Recado 4", "Descrição do recado 4", "Professor 4", "04/01/2023"),

        )

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(publicacoes) { publicacao ->
            CardNucleoLarge(
                publicacao.titulo,
                publicacao.descricao,
                publicacao.data,
                publicacao.autor,
            )
        }
    }
}

@Composable
fun PublicacoesScreen() {

    Scaffold(
        topBar = {
            Row(modifier = Modifier.padding(30.dp)) {
                AppIcons.Menu(AzulPrincipal)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Text(
                    text = "Publicações",
                    style = AppTypography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = AzulPrincipal,
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                )
            }
            listaDeRecados()

        }

    }
}

@Composable
@Preview(showBackground = true)
fun PublicacoesScreenPreview() {
    NucleoFornariTheme {
        PublicacoesScreen()
    }
}