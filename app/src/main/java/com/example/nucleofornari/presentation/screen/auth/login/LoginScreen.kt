package com.example.nucleofornari.presentation.screen.auth.login

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.nucleofornari.data.model.SessaoUsuario
import com.example.nucleofornari.data.model.usuario.UsuarioLoginDto
import com.example.nucleofornari.data.model.usuario.UsuarioTokenDto
import com.example.nucleofornari.data.remote.RetrofitClient
import com.example.nucleofornari.data.remote.UsuarioApiService
import com.example.nucleofornari.presentation.common.theme.AzulPrincipal
import com.example.nucleofornari.presentation.common.theme.NucleoFornariTheme
import com.example.nucleofornari.presentation.common.component.AppIcons
import com.example.nucleofornari.presentation.common.component.BlueButton
import com.example.nucleofornari.presentation.common.component.Header
import com.example.nucleofornari.presentation.common.component.NucleoTextField
import com.example.nucleofornari.presentation.navigation.BottomBarScreen
import com.example.nucleofornari.util.UiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = koinViewModel()
){

    val uiState by viewModel.uiState.collectAsState()

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Scaffold(
        topBar = { Header("Voltar", bgcolor = Color.Transparent, textColor = Color.Black, iconColor = Color.Black, onClick = {navController.navigate("auth")}) }
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

                NucleoTextField(
                    labelText = "Email",
                    value = email,
                    onValueChange = { email = it }
                )

                NucleoTextField(
                    labelText = "Senha",
                    value = senha,
                    onValueChange = { senha = it }
                )

                Text(text = "Esqueceu a senha?", color = AzulPrincipal, modifier = Modifier.clickable { navController.navigate("esqueceu_senha") })
            }
            Spacer(Modifier.height(100.dp))
            BlueButton("Continuar SEM login", AzulPrincipal, onClick = {navController.navigate("main")})

            BlueButton("Continuar", AzulPrincipal, onClick = {
                viewModel.login(email, senha)
            })

            when (uiState) {
                is UiState.Loading -> {
                    CircularProgressIndicator()
                }

                is UiState.Error -> {
                    Text(
                        text = (uiState as UiState.Error).message,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }

                is UiState.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate("main") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                }

                else -> {}
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//
//    NucleoFornariTheme{
//        LoginScreen(
//            navController = rememberNavController(),
//            viewModel = FakeLoginViewModel()
//        )
//    }
//}