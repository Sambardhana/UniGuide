package com.uniguide.app.ui.splash

import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uniguide.app.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {

    // Controls the size of the logo
    val logoScale = remember {
        Animatable(2.2f)
    }

    // Controls the transparency of the logo
    val logoAlpha = remember {
        Animatable(0f)
    }

    LaunchedEffect(Unit) {

        // ----------------------------------------
        // STEP 1: Logo appears
        // ----------------------------------------

        logoAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500
            )
        )

        // ----------------------------------------
        // STEP 2: Logo zooms out
        // ----------------------------------------

        logoScale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1200,
                easing = FastOutSlowInEasing
            )
        )

        // ----------------------------------------
        // STEP 3: Keep logo visible briefly
        // ----------------------------------------

        delay(500)

        // ----------------------------------------
        // STEP 4: Go to Welcome Page
        // ----------------------------------------

        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFF3FBFD)
            ),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(
                id = R.drawable.uniguide_logo
            ),

            contentDescription = "UniGuide Logo",

            modifier = Modifier
                .size(220.dp)
                .graphicsLayer(
                    scaleX = logoScale.value,
                    scaleY = logoScale.value,
                    alpha = logoAlpha.value
                )
        )
    }
}