package com.example.nucleofornari

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nucleofornari.navigation.AppNavigation
import com.example.nucleofornari.ui.theme.AzulPrincipal
import com.example.nucleofornari.ui.theme.NucleoFornariTheme
import com.example.nucleofornari.ui.theme.components.TextButton
import com.example.nucleofornari.ui.theme.components.WhiteButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NucleoFornariTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = AzulPrincipal)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(R.drawable.logobranco),
                contentDescription = "Logo Núcleo Fornari",
                modifier = Modifier.size(300.dp),
//                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = modifier
                    .padding(bottom = 32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WhiteButton("Entrar", handleCLick = ({}))
                TextButton("Ainda não tem uma conta?", Color.White)
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NucleoFornariTheme {
        Greeting()
    }
}