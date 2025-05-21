package com.example.nucleofornari.presentation.common.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nucleofornari.presentation.common.theme.AzulPrincipal
import com.example.nucleofornari.presentation.common.theme.PretoPrincipal

@Composable
fun NucleoTextField(labelText: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(labelText) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AzulPrincipal,
            focusedPlaceholderColor = AzulPrincipal,
            focusedLabelColor = AzulPrincipal,
            focusedTextColor = PretoPrincipal
        )
    )
}

@Composable
fun NucleoLongTextField(
    labelText: String,
    value: String,
    valueChangeHandler: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = { newText -> valueChangeHandler(newText) },
        label = { Text(labelText) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AzulPrincipal,
            focusedPlaceholderColor = AzulPrincipal,
            focusedLabelColor = AzulPrincipal,
            focusedTextColor = PretoPrincipal
        ),
        modifier = Modifier
            .height(250.dp)
            .width(300.dp)
    )
}

//@Preview(showBackground = true)
//@Composable
//fun NucleoTextFieldPreview() {
//    NucleoLongTextField("Pedrinho")
//}