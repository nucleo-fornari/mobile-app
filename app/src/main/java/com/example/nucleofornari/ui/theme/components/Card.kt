package com.example.nucleofornari.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nucleofornari.ui.theme.AzulPrincipal
import com.example.nucleofornari.ui.theme.PretoPrincipal
import com.example.nucleofornari.ui.theme.Success

@Composable
fun CardNucleo(cardTitle: String, cardIcon: @Composable () -> Unit, onclick: () -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .size(width = 300.dp, height = 60.dp)
            .clickable { onclick() }

    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = cardTitle,
            )

            cardIcon()


        }

    }
}

@Composable
fun CardNucleoLarge(
    titulo: String,
    descricao: String,
    data: String,
    autor: String,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .width(300.dp)
            .height(180.dp)
//            .border(
//                width = 1.dp,
//                color = Color.LightGray
//            )
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .border(2.dp, Color.LightGray, RoundedCornerShape(12.dp))
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier.padding(bottom = 20.dp),
                text = titulo,
                style = com.example.nucleofornari.ui.theme.AppTypography.titleMedium,
                color = AzulPrincipal
            )
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = descricao,
                style = com.example.nucleofornari.ui.theme.AppTypography.bodyMedium,
                color = PretoPrincipal
            )

            Text(
                text = autor,
                style = com.example.nucleofornari.ui.theme.AppTypography.bodySmall,
                color = PretoPrincipal,
                fontWeight = com.example.nucleofornari.ui.theme.AppTypography.bodySmall.fontWeight,
                fontStyle = com.example.nucleofornari.ui.theme.AppTypography.bodySmall.fontStyle,
            )

            Text(
                text = data,
                style = com.example.nucleofornari.ui.theme.AppTypography.bodySmall,
                color = Color.Gray,
                fontStyle = com.example.nucleofornari.ui.theme.AppTypography.bodySmall.fontStyle,
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun CardPreview() {
    CardNucleo("Aluno em crise ", { AppIcons.CheckCircle(Success) }, onclick = {})
    CardNucleoLarge(
        "Lição de casa - Matemática",
        "Resolver as páginas 10 a 12 do livro de matemática.",
        "Segunda-feira, 3 de Novembro.",
        "Por Profª Ana,"
    )
}