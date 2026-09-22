package com.uniguide.app.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// ===============================
// UniGuide Color Palette
// ===============================

val UniGuideTeal = Color(0xFF0F7187)

val UniGuideTealLight = Color(0xFF5D9CA6)

val UniGuideLightTeal = Color(0xFFCCE4E7)

val UniGuideBackground = Color(0xFFF3FBFD)

val UniGuideWhite = Color(0xFFFFFFFF)

val UniGuideDarkText = Color(0xFF1E2A2E)

val UniGuideSecondaryText = Color(0xFF5D7379)

val UniGuideGreen = Color(0xFF607B5E)

val UniGuideGold = Color(0xFFC4B77B)


// ===============================
// UniGuide Material 3 Color Scheme
// ===============================

private val UniGuideColorScheme = lightColorScheme(

    primary = UniGuideTeal,

    onPrimary = UniGuideWhite,

    primaryContainer = UniGuideLightTeal,

    onPrimaryContainer = UniGuideDarkText,

    secondary = UniGuideTealLight,

    onSecondary = UniGuideWhite,

    secondaryContainer = UniGuideLightTeal,

    onSecondaryContainer = UniGuideDarkText,

    background = UniGuideBackground,

    onBackground = UniGuideDarkText,

    surface = UniGuideWhite,

    onSurface = UniGuideDarkText,

    surfaceVariant = UniGuideLightTeal,

    onSurfaceVariant = UniGuideSecondaryText
)


// ===============================
// UniGuide Theme
// ===============================

@Composable
fun UniGuideTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(

        colorScheme = UniGuideColorScheme,

        typography = Typography(),

        content = content
    )
}
