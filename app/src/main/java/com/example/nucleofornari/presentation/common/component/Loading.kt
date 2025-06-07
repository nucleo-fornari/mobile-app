package com.example.nucleofornari.presentation.common.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.example.nucleofornari.presentation.common.theme.AzulPrincipal

@Composable
fun NucleoLoading(){
    CircularProgressIndicator(color = AzulPrincipal)
}