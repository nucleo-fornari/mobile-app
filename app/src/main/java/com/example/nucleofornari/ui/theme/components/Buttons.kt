package com.example.nucleofornari.ui.theme.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nucleofornari.ui.theme.AzulPrincipal

@Composable
fun WhiteButton(text: String, handleCLick: () -> Unit) {
    Button(
        onClick = { handleCLick() },
        modifier = Modifier.size(302.dp, 60.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = AzulPrincipal
        ),
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(
            text = text
        )
    }
}

@Composable
fun TextButton(text: String, color: Color) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        elevation = null
    ) {
        Text(
            text = text,
            color = color
        )
    }
}

@Composable
fun BlueButton(text: String, color: Color) {
    Button(
        onClick = { },
        modifier = Modifier.size(302.dp, 60.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = AzulPrincipal,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(
            text = text
        )
    }
}
