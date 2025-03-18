package com.example.nucleofornari.ui.theme.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.nucleofornari.ui.theme.AzulPrincipal

@Composable
fun NucleoCheckbox(checkboxText: String) {
    var checked by remember { mutableStateOf(true) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked, onCheckedChange = { checked = it },
            colors = CheckboxDefaults.colors(AzulPrincipal)
        )

        Text(text = checkboxText)
    }


}

@Preview (showBackground = true)
@Composable
fun NucleoCheckboxPreview() {
    NucleoCheckbox("Problema infraestrutura")
}