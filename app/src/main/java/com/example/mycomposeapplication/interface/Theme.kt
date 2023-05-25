package com.example.mycomposeapplication.`interface`

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun LightTheme(content: @Composable () -> Unit) {
    val colors = lightColors(
        primary = Color(0xFF6200EE),
        primaryVariant = Color(0xFF3700B3),
        onPrimary = Color.White,
        secondary = Color(0xFF03DAC5),
        secondaryVariant = Color(0xFF018786),
        onSecondary = Color.Black
    )
    MaterialTheme(colors = colors) {
        content()
    }
}

@Composable
fun DarkTheme(content: @Composable () -> Unit) {
    val colors = darkColors(
        primary = Color(0xFFBB86FC),
        primaryVariant = Color(0xFF3700B3),
        onPrimary = Color.Black,
        secondary = Color(0xFF03DAC5),
        secondaryVariant = Color(0xFF03DAC5)
    )
    MaterialTheme(colors = colors) {
        content()
    }
}
