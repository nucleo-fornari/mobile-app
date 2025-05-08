package com.example.nucleofornari.presentation.screen.responsavel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePicker
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nucleofornari.presentation.common.component.AppIcons
import com.example.nucleofornari.presentation.common.component.BlueButton
import com.example.nucleofornari.presentation.common.component.CardNucleo
import com.example.nucleofornari.presentation.common.component.DatePickerFieldToModalNucleo
import com.example.nucleofornari.presentation.common.component.DatePickerNucleo
import com.example.nucleofornari.presentation.common.component.Header
import com.example.nucleofornari.presentation.common.component.NucleoLongTextField
import com.example.nucleofornari.presentation.common.component.NucleoSwitch
import com.example.nucleofornari.presentation.common.theme.NucleoFornariTheme
import com.example.nucleofornari.presentation.common.theme.PretoPrincipal
import com.example.nucleofornari.presentation.navigation.BottomBarScreen

@Composable
fun ReunioesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            Header(
                "Solicitar reunião",
                onClick = { navController.navigate(BottomBarScreen.Chamado.route) })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardNucleo(
                "Selecione a categoria",
                { AppIcons.ArrowForward(PretoPrincipal) },
                onclick = { navController.navigate("selecionar_categoria") })
            NucleoLongTextField("Descreva o motivo da reunião")

            Row(
                modifier = Modifier
                    .width(300.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DatePickerFieldToModalNucleo()
            }

            BlueButton(
                "Enviar",
                Color.White,
                onClick = { navController.navigate("chamado_enviado") })
        }
    }
}


@Composable
@Preview(showBackground = true)
fun ReunioesScreenPreview() {
    NucleoFornariTheme {
        ReunioesScreen(navController = rememberNavController())
    }
}