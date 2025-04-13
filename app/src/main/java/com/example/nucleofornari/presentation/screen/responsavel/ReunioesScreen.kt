package com.example.nucleofornari.presentation.screen.responsavel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.nucleofornari.presentation.common.theme.NucleoFornariTheme

@Composable
fun ReunioesScreen(navController: NavController){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(
            text = "Reuniões Resposável",
            fontSize = 30.sp,
            fontFamily = FontFamily.Serif
        )
    }

}

@Composable
@Preview(showBackground = true)
fun ReunioesScreenPreview() {
    NucleoFornariTheme {
        ReunioesScreen(navController = rememberNavController())
    }
}