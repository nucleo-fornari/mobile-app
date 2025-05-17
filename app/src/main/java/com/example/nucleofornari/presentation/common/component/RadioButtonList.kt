package com.example.nucleofornari.presentation.common.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nucleofornari.presentation.common.theme.AzulPrincipal

@Composable
fun NucleoRadioButtonList(
    radioOptions: List<String>,
    selectedOptionIndex: Int,
    onOptionSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.selectableGroup()) {
        radioOptions.forEachIndexed { index, text ->
            Row(
                Modifier
                    .height(56.dp)
                    .selectable(
                        selected = (index == selectedOptionIndex),
                        onClick = { onOptionSelected(index) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (index == selectedOptionIndex),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = AzulPrincipal
                    ),
                    onClick = null
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//
//fun NucleoRadioButtonListPreview() {
//    val radioOptions = listOf("Calls", "Missed", "Friendeee")
//    NucleoRadioButtonList(radioOptions)
//}