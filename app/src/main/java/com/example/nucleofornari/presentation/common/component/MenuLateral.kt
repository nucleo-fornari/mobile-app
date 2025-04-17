package com.example.nucleofornari.presentation.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.nucleofornari.R
import com.example.nucleofornari.presentation.common.theme.AzulPrincipal

@Composable
fun MenuLateral(
    drawerState: DrawerState,
    content: @Composable () -> Unit
) {
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
                            .fillMaxSize()
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
                    Column{
                        Text(
                            text = "Recados",
                            color = Color.White,
                            style = Typography().titleLarge,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Text(
                            text = "Eventos",
                            color = Color.White,
                            style = Typography().titleLarge,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Text(
                            text = "Configurações",
                            color = Color.White,
                            style = Typography().titleLarge,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        },
        content = {
            content()
        }
    )
}
