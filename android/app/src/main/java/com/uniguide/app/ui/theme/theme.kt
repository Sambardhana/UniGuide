package com.uniguide.app.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val UniGuideColorScheme = lightColorScheme(

    primary = Color(0xFF0F7187),

    onPrimary = Color.White,

    primaryContainer = Color(0xFFCCE4E7),

    onPrimaryContainer = Color(0xFF1E2A2E),

    secondary = Color(0xFF5D9CA6),

    onSecondary = Color.White,

    background = Color(0xFFF3FBFD),

    onBackground = Color(0xFF1E2A2E),

    surface = Color.White,

    onSurface = Color(0xFF1E2A2E)
)

@Composable
fun UniGuideTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = UniGuideColorScheme,
        content = content
    )
}