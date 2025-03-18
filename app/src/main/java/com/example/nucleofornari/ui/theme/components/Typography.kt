package com.example.nucleofornari.ui.theme.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.nucleofornari.R

// Carregue a fonte 'inter_tight' em FontFamily
val interTightFontFamily = FontFamily(
    Font(R.font.inter_regular)
)

@Composable
fun Headline1(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = interTightFontFamily,
            fontSize = 93.sp
        )
    )
}

@Composable
fun Headline2(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = interTightFontFamily,
            fontSize = 58.sp
        )
    )
}

@Composable
fun Headline3(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = interTightFontFamily,
            fontSize = 47.sp
        )
    )
}

@Composable
fun Headline4(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = interTightFontFamily,
            fontSize = 33.sp
        )
    )
}

@Composable
fun Headline5(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = interTightFontFamily,
            fontSize = 23.sp
        )
    )
}

@Composable
fun Headline6(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = interTightFontFamily,
            fontSize = 19.sp
        )
    )
}

@Composable
fun Subtitle1(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = interTightFontFamily,
            fontSize = 16.sp
        )
    )
}

@Composable
fun Subtitle2(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = interTightFontFamily,
            fontSize = 14.sp
        )
    )
}

@Composable
fun Body1(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = interTightFontFamily,
            fontSize = 16.sp
        )
    )
}

@Composable
fun Body2(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = interTightFontFamily,
            fontSize = 14.sp
        )
    )
}

@Composable
fun ButtonText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = interTightFontFamily,
            fontSize = 14.sp
        )
    )
}

@Composable
fun Caption(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = interTightFontFamily,
            fontSize = 12.sp
        )
    )
}

@Composable
fun Overline(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = interTightFontFamily,
            fontSize = 10.sp
        )
    )
}

@Preview(showBackground = true)
@Composable
fun TypographyPreview() {
    Column {
        Headline1("Headline1")
        Headline2("Headline2")
        Headline3("Headline3")
        Headline4("Headline4")
        Headline5("Headline5")
        Headline6("Headline6")
        Subtitle1("Subtitle1")
        Subtitle2("Subtitle2")
        Body1("Body1")
        Body2("Body2")
        ButtonText("ButtonText")
        Caption("Caption")
        Overline("Overline")
    }
}
