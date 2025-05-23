package com.example.nucleofornari.presentation.screen.professor

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nucleofornari.R
import com.example.nucleofornari.presentation.common.theme.AzulPrincipal
import com.example.nucleofornari.presentation.common.theme.PretoPrincipal
import com.example.nucleofornari.presentation.common.component.NucleoTextButton

@Composable
fun ChamadoEnviadoScreen(navController: NavController) {
//    val interRegular = FontFamily(Font(R.font.inter_regular))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.chamadoenviado),
            contentDescription = "Logo Núcleo Fornari",
            modifier = Modifier.size(300.dp)
                .padding(bottom = 32.dp)
        )

        Text(
            text = "Chamado enviado!",
//            fontFamily = interRegular,
            fontSize = 32.sp,
            color = PretoPrincipal

        )

        NucleoTextButton("Acompanhar resolução do chamado", AzulPrincipal, onClick = {navController.navigate("chamado")})

    }


}


@Preview
@Composable
fun ChamadoEnviadoScreenPreview() {
    ChamadoEnviadoScreen(navController = rememberNavController())
}