package com.example.nucleofornari.ui.theme.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable

import com.example.nucleofornari.ui.theme.AzulPrincipal

@Composable
fun NucleoLoading(){
    CircularProgressIndicator(color = AzulPrincipal)
}