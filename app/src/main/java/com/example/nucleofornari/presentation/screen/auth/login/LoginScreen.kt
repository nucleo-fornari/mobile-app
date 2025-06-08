package com.example.nucleofornari.presentation.screen.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import com.example.nucleofornari.presentation.common.component.NucleoLoading
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nucleofornari.R
import com.example.nucleofornari.domain.model.usuario.UsuarioTokenDto
import com.example.nucleofornari.presentation.common.theme.AzulPrincipal
import com.example.nucleofornari.presentation.common.component.BlueButton
import com.example.nucleofornari.presentation.common.component.Header
import com.example.nucleofornari.presentation.common.component.NucleoCheckbox
import com.example.nucleofornari.presentation.common.component.NucleoTextField
import com.example.nucleofornari.presentation.common.component.PasswordInputField
import com.example.nucleofornari.util.UiState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.TextButton
import com.example.nucleofornari.presentation.common.component.PoliticaLGPDText

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel
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
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 64.dp)
            ) {
                Text(text = "Entre com sua conta", color = AzulPrincipal, fontWeight = FontWeight.Bold, fontSize = 30.sp)

                NucleoTextField(
                    labelText = "Email",
                    value = email,
                    onValueChange = { email = it }
                )

                PasswordInputField(
                    password = senha,
                    onPasswordChange = { senha = it }
                )

                Text(text = "Esqueceu a senha?", color = AzulPrincipal, modifier = Modifier.clickable { navController.navigate("esqueceu_senha") })

NucleoCheckbox(checkboxText = "Li e aceito os termos da política de privacidade.")
                // Ação quando o checkbox for clicado
                // Aqui você pode implementar a lógica para aceitar os termos
                // Ação quando o checkbox for clicado
                // Aqui você pode implementar a lógica para aceitar os termos
            }
            Spacer(Modifier.height(48.dp))

            BlueButton("Continuar", AzulPrincipal, onClick = {
                viewModel.login(email, senha)
            })

            Spacer(Modifier.height(16.dp))


            when (uiState) {
                is UiState.Loading -> {
                    NucleoLoading()
                }

                is UiState.Error -> {
                    Text(
                        text = (uiState as UiState.Error).message,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }

                is UiState.Success -> {
                    val user = (uiState as UiState.Success<UsuarioTokenDto>).data

                    LaunchedEffect(user.userId) {
                        if (user.funcao == "PROFESSOR") {
                            navController.navigate("main") {
                                popUpTo("login") { inclusive = true }
                            }
                        }
                        else if(user.funcao == "RESPONSAVEL"){
                            navController.navigate("responsavel") {
                                popUpTo("login") { inclusive = true }
                            }
                        }
                    }
                }

                else -> {}
            }
        }
    }
}

@Composable
fun PoliticaLGPDModal(
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    text = "Política de Privacidade e Proteção de Dados Pessoais - Núcleo Fornari",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            text = {
                // Scrollable content
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    PoliticaLGPDText()
                }
            },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text("Fechar")
                }
            }
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    NucleoFornariTheme{
//        LoginScreen(
//            navController = rememberNavController(),
//            viewModel = FakeLoginViewModel()
//        )
//    }
//}
