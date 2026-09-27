package com.uniguide.app.ui.splash

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uniguide.app.R
import com.uniguide.app.ui.theme.UniGuideBackground
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {

    var logoScale by remember {
        mutableFloatStateOf(1.5f)
    }

    val animatedScale by animateFloatAsState(
        targetValue = logoScale,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        ),
        label = "LogoZoom"
    )

    LaunchedEffect(Unit) {

        // 1. Logo starts large
        logoScale = 1.5f

        // Small delay
        delay(300)

        // 2. Zoom OUT
        logoScale = 0.7f

        // Wait for zoom-out animation
        delay(1000)

        // 3. Zoom IN
        logoScale = 1.0f

        // Wait for zoom-in animation
        delay(1000)

        // 4. Go to Welcome Screen
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(UniGuideBackground),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(
                id = R.drawable.uniguide_logo
            ),
            contentDescription = "UniGuide Logo",
            modifier = Modifier
                .size(180.dp)
                .scale(animatedScale)
        )
    }
}