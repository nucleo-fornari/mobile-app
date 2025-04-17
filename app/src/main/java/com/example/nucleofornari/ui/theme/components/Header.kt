package com.example.nucleofornari.ui.theme.components


import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nucleofornari.ui.theme.AzulPrincipal


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(pageTitle: String) {
    TopAppBar(
        modifier = Modifier
            .padding(bottom = 16.dp),
        title = { Text(pageTitle, color = Color.White) },
        navigationIcon = {
            IconButton(onClick = { /* Ação do Menu / }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = { / Ação de Opções */
            }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Mais Opções",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AzulPrincipal
        )
    )
}
