package com.example.nucleofornari.presentation.screen.responsavel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nucleofornari.presentation.common.component.Header
import com.example.nucleofornari.presentation.common.theme.PretoPrincipal
import com.example.nucleofornari.presentation.common.component.AppIcons
import com.example.nucleofornari.presentation.common.component.BlueButton
import com.example.nucleofornari.presentation.common.component.CardNucleo
import com.example.nucleofornari.presentation.common.component.NucleoLongTextField
import com.example.nucleofornari.presentation.common.component.NucleoSwitch
import com.example.nucleofornari.presentation.navigation.BottomBarScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SolicitarReuniaoScreen(navController: NavController) {
    Scaffold(
        topBar = { Header("Solicitar reunião", onClick = {navController.navigate(BottomBarScreen.Chamado.route)}) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            }
            CardNucleo("Selecione a categoria", { AppIcons.ArrowForward(PretoPrincipal) }, onclick = {navController.navigate("selecionar_categoria")})
            NucleoLongTextField("O que está acontecendo?")

            Row(
                modifier = Modifier
                    .width(300.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Criança atípica ou em observação?"
                )
                NucleoSwitch()
            }

            BlueButton("Concluir", Color.White, onClick = {navController.navigate("chamado_enviado")})
        }
    }



@Preview
@Composable
fun SolicitarReuniaoPreview() {
    SolicitarReuniaoScreen(navController = rememberNavController())
}