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
import com.example.nucleofornari.presentation.common.component.GrayButton
import com.example.nucleofornari.presentation.common.component.Header
import com.example.nucleofornari.presentation.common.component.NucleoTextField

@Composable
fun CodigoScreen(navController: NavController){
    var codigo by remember { mutableStateOf("") }

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
                painter = painterResource(R.drawable.codigologin),
                contentDescription = "Imagem Confirme o código",
                modifier = Modifier.size(250.dp),
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Confirme o código", color = AzulPrincipal, fontWeight = FontWeight.Bold, fontSize = 30.sp)
                Text(
                    text = "Caso não encontre, verifique a caixa de spam ou solicite um novo código.",
                    color = AzulPrincipal,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(300.dp)
                )
                NucleoTextField(
                    labelText = "Código",
                    value = codigo,
                    onValueChange = { codigo = it }
                )

            }
            Spacer(Modifier.height(100.dp))

            GrayButton("Reenviar",onClick = {})
            Spacer(Modifier.height(20.dp))
            BlueButton("Confirmar", AzulPrincipal, onClick = {navController.navigate("redefinir_senha")})

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CodigoScreenPreview() {
    NucleoFornariTheme{
        CodigoScreen(navController = rememberNavController())
    }
}