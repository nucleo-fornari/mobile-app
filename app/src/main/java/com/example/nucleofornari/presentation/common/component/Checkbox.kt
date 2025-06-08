package com.example.nucleofornari.presentation.common.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nucleofornari.presentation.common.theme.AzulPrincipal
import com.example.nucleofornari.presentation.common.theme.PretoPrincipal
import com.example.nucleofornari.presentation.screen.auth.login.PoliticaLGPDModal

@Composable
fun NucleoCheckbox(checkboxText: String) {
    var checked by remember { mutableStateOf(true) }
    var showDialog by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked, onCheckedChange = { checked = it },
            colors = CheckboxDefaults.colors(AzulPrincipal)
        )

        Text(text = checkboxText, fontSize = 12.sp, style = TextStyle(color = PretoPrincipal), modifier = Modifier.clickable { showDialog = true })
    }

    PoliticaLGPDModal(showDialog = showDialog, onDismiss = { showDialog = false })
}

//@Preview (showBackground = true)
//@Composable
//fun NucleoCheckboxPreview() {
//    NucleoCheckbox("Problema infraestrutura")
//}