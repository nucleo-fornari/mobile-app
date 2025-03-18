package com.example.nucleofornari.ui.theme.components

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.nucleofornari.ui.theme.AzulPrincipal

@Composable
fun NucleoSwitch() {
    var checked by remember { mutableStateOf(true) }

    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.White,
            checkedTrackColor = AzulPrincipal
        )
    )
}

@Preview (showBackground = true)
@Composable
fun NucleoSwitchPreview() {
    NucleoSwitch()
}