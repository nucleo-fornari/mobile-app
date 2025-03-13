package com.example.nucleofornari

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nucleofornari.ui.theme.Success
import com.example.nucleofornari.ui.theme.components.AppIcons
import com.example.nucleofornari.ui.theme.components.BlueButton
import com.example.nucleofornari.ui.theme.components.CardNucleo

@Composable
fun Chamados() {
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
            CardNucleo("Junin cagou na sala", { AppIcons.CheckCircle(Success) })
            CardNucleo("Junin cagou na sala", { AppIcons.CheckCircle(Success) })
            CardNucleo("Junin cagou na sala", { AppIcons.CheckCircle(Success) })


            BlueButton("Abrir chamado", Color.White)
        }
    }
}

@Composable
@Preview
fun ChamadosPreview() {
    Chamados()
}