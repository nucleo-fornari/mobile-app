package com.example.nucleofornari

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.nucleofornari.presentation.navigation.RootNavGraph
import com.example.nucleofornari.presentation.common.theme.NucleoFornariTheme
import com.example.nucleofornari.presentation.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NucleoFornariTheme {
                App()
            }
        }
    }
}

// to merge

@Composable
fun App() {
    val navController = rememberNavController()
    RootNavGraph(navController = navController)
}

@Composable
@Preview
fun MainActivityPreview(){
    App()
}