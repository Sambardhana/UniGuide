package com.uniguide.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.uniguide.app.navigation.UniGuideNavGraph


class StudentActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                UniGuideNavGraph()
            }
        }
    }
}
