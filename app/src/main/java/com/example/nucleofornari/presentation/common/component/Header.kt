package com.example.nucleofornari.presentation.common.component


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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.nucleofornari.presentation.common.theme.AzulPrincipal


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(pageTitle: String, bgcolor:Color = AzulPrincipal, textColor:Color = Color.White, iconColor:Color = Color.White, navIcon: ImageVector = Icons.AutoMirrored.Filled.ArrowBack, onClick: () -> Unit) {
    TopAppBar(
        modifier = Modifier
            .padding(bottom = 16.dp),
        title = { Text(pageTitle, color = textColor) },
        navigationIcon = {
            IconButton(onClick = onClick) {
                Icon(
                    navIcon,
                    contentDescription = "Ícone de Navegação",
                    tint = iconColor
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = bgcolor
        )
    )
}
