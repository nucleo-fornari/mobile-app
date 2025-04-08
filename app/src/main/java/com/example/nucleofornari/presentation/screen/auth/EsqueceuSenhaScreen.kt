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
import com.example.nucleofornari.presentation.common.component.NucleoTextField

@Composable
fun EsqueceuSenhaScreen(navController: NavController){


    Scaffold(
        topBar = {
            Row(modifier = Modifier.padding(30.dp).clickable { navController.navigate("auth") }) {
                AppIcons.ArrowBack(Color.DarkGray)
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "Voltar", color = Color.DarkGray)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.emaillogin),
                contentDescription = "Imagem Esqueceu sua senha",
                modifier = Modifier.size(250.dp),
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Esqueceu sua senha?", color = AzulPrincipal, fontWeight = FontWeight.Bold, fontSize = 30.sp)
                Text(
                    text = "Por favor, informe o e-mail associado à sua conta que enviaremos um link com as instruções para recuperar a sua senha.",
                    color = AzulPrincipal,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(300.dp)
                )
                NucleoTextField("Email")


            }
            Spacer(Modifier.height(100.dp))
            BlueButton("Continuar", AzulPrincipal, onClick = {navController.navigate("codigo")})

        }
    }
}

@Preview(showBackground = true)
@Composable
fun EsqueceuSenhaScreenPreview() {
    NucleoFornariTheme{
        EsqueceuSenhaScreen(navController = rememberNavController())
    }
}