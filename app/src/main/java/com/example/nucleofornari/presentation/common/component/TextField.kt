package com.example.nucleofornari.presentation.common.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
fun PasswordInputField(
    password: String,
    onPasswordChange: (String) -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Senha") },
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val icon = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
            val description = if (passwordVisible) "Ocultar senha" else "Mostrar senha"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = icon, contentDescription = description)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            autoCorrect = false,
            imeAction = ImeAction.Done
        ),
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