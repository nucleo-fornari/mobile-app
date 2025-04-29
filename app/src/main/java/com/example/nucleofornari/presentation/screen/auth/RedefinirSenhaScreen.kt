package com.example.nucleofornari.presentation.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nucleofornari.R
import com.example.nucleofornari.presentation.common.theme.AzulPrincipal
import com.example.nucleofornari.presentation.common.theme.NucleoFornariTheme
import com.example.nucleofornari.presentation.common.component.AppIcons
import com.example.nucleofornari.presentation.common.component.BlueButton
import com.example.nucleofornari.presentation.common.component.Header
import com.example.nucleofornari.presentation.common.component.NucleoTextField

@Composable
fun RedefinirSenhaScreen(navController: NavController){
    var senha by remember { mutableStateOf("") }
    var senha2 by remember { mutableStateOf("") }


    Scaffold(
        topBar = { Header("Voltar", bgcolor = Color.Transparent, textColor = Color.Black, iconColor = Color.Black, onClick = {navController.navigate("auth")}) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.redefinirlogin),
                contentDescription = "Imagem Redefinir a senha",
                modifier = Modifier.size(250.dp),
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Redefinir a senha", color = AzulPrincipal, fontWeight = FontWeight.Bold, fontSize = 30.sp)
                Text(
                    text = "Crie uma nova senha.",
                    color = AzulPrincipal,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(300.dp)
                )

                NucleoTextField(
                    labelText = "Digite a nova senha",
                    value = senha,
                    onValueChange = { senha = it }
                )
                NucleoTextField(
                    labelText = "Confirme a nova senha",
                    value = senha2,
                    onValueChange = { senha2 = it }
                )

            }
            Spacer(Modifier.height(100.dp))
            BlueButton("Redefinir", AzulPrincipal, onClick = {navController.navigate("redefinir_confirm")})

        }
    }
}

@Preview(showBackground = true)
@Composable
fun RedefinirSenhaScreenPreview() {
    NucleoFornariTheme{
        RedefinirSenhaScreen(navController = rememberNavController())
    }
}