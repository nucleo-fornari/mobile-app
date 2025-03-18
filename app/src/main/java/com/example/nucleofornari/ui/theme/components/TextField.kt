package com.example.nucleofornari.ui.theme.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nucleofornari.ui.theme.AzulPrincipal
import com.example.nucleofornari.ui.theme.PretoPrincipal

@Composable
fun NucleoTextField() {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { newText -> text = newText },
        label = { Text("Digite algo") },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AzulPrincipal,
            focusedPlaceholderColor = AzulPrincipal,
            focusedLabelColor = AzulPrincipal,
            focusedTextColor = PretoPrincipal
        )
    )
}

@Composable
fun NucleoLongTextField(labelText: String) {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { newText -> text = newText },
        label = { Text(labelText) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AzulPrincipal,
            focusedPlaceholderColor = AzulPrincipal,
            focusedLabelColor = AzulPrincipal,
            focusedTextColor = PretoPrincipal
        ),
        modifier = Modifier
            .height(300.dp)
            .width(300.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun NucleoTextFieldPreview() {
    NucleoLongTextField("Pedrinho")
}