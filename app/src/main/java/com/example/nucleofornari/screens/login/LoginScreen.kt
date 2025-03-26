package com.example.nucleofornari.screens.login

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nucleofornari.R
import com.example.nucleofornari.screens.professor.InicioProfessorScreen
import com.example.nucleofornari.ui.theme.AzulPrincipal
import com.example.nucleofornari.ui.theme.components.AppIcons
import com.example.nucleofornari.ui.theme.components.BlueButton
import com.example.nucleofornari.ui.theme.components.NucleoTextField

@Composable
fun LoginScreen(navController: NavController){

    Scaffold(
        topBar = {
            Row(modifier = Modifier.padding(30.dp).clickable { navController.navigate("auth") }) {
                AppIcons.ArrowBack(Color.DarkGray)
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "Voltar", color = Color.DarkGray)
            }
        }
    ) {
        innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.imglogin),
                contentDescription = "Imagem Login",
                modifier = Modifier.size(250.dp),
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = "Entre com sua conta", color = AzulPrincipal, fontWeight = FontWeight.Bold, fontSize = 30.sp)

                NucleoTextField("Email")
                NucleoTextField("Senha")

                Text(text = "Esqueceu a senha?", color = AzulPrincipal, modifier = Modifier.clickable { navController.navigate("esqueceu_senha") })
            }
            Spacer(Modifier.height(100.dp))
            BlueButton("Continuar", AzulPrincipal, onClick = {navController.navigate("main")})



        }
    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    com.example.nucleofornari.ui.theme.NucleoFornariTheme{
        LoginScreen(navController = rememberNavController())
    }
}