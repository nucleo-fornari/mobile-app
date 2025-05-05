package com.example.nucleofornari.presentation.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nucleofornari.R
import com.example.nucleofornari.presentation.common.theme.AzulPrincipal
import com.example.nucleofornari.presentation.common.theme.Error
import com.example.nucleofornari.presentation.common.theme.PretoPrincipal

@Composable
fun MenuLateral(
    drawerState: DrawerState,
    content: @Composable () -> Unit,
    navController: NavController
) {
    var showDialog by remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        modifier = Modifier.background(color = AzulPrincipal),
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(drawerContainerColor = AzulPrincipal) {
                Text("Meu perfil", modifier = Modifier.padding(16.dp), color = Color.White)
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp)
                        .background(color = AzulPrincipal),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                )
                {
                    Image(
                        painter = painterResource(R.drawable.fotousuario),
                        contentDescription = "Foto do usuário",
                        modifier = Modifier.size(80.dp),
                    )
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                        Text(
                            text = "Nome do Usuário",
                            color = Color.White,
                            style = Typography().titleLarge,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Text(
                            text = "Responsável",
                            color = Color.White,
                            style = Typography().bodyMedium,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
                    ){

                        Text(
                            text = "Email:",
                            color = Color.White,
                            style = Typography().titleMedium,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Text(
                            text = "Telefone:",
                            color = Color.White,
                            style = Typography().titleMedium,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 64.dp)
                            .clickable { showDialog = true },

                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Text(
                        text = "Sair",
                        color = Color.White,
                        style = Typography().titleMedium,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        AppIcons.Exit(color = Color.White)
                    }
                }
            }
        },
        content = {
            content()
        },
    )
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(text = "Deseja sair do app?",
                    color = AzulPrincipal,
                    style = Typography().titleLarge
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    navController.navigate("login") {

                        popUpTo(0)
                    }
                }) {
                    Text("Sair", color = Error)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                }) {
                    Text("Cancelar", color = PretoPrincipal)
                }
            }
        )
    }
}
