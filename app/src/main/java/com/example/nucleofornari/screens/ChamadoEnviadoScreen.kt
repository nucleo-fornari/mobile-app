package com.example.nucleofornari.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.example.nucleofornari.R
import com.example.nucleofornari.ui.theme.AzulPrincipal
import com.example.nucleofornari.ui.theme.PretoPrincipal
import com.example.nucleofornari.ui.theme.components.NucleoTextButton

@Composable
fun ChamadoEnviadoScreen() {
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

        NucleoTextButton("Acompanhar resolução do chamado", AzulPrincipal)

    }


}


@Preview
@Composable
fun ChamadoEnviadoScreenPreview() {
    ChamadoEnviadoScreen()
}