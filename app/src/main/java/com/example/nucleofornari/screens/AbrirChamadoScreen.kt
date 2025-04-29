package com.example.nucleofornari.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nucleofornari.ui.theme.PretoPrincipal
import com.example.nucleofornari.ui.theme.components.AppIcons
import com.example.nucleofornari.ui.theme.components.CardNucleo

@Composable
fun AbrirChamadoScreen() {
    Scaffold(
        topBar = { com.example.nucleofornari.ui.theme.components.Header("Meus chamados") }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardNucleo("Tipo de chamado", {AppIcons.ArrowForward(PretoPrincipal)})
        }
    }
}


@Preview
@Composable
fun AbriChamadoScreenPreview(){
    AbrirChamadoScreen()
}