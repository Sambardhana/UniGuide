package com.uniguide.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.uniguide.app.navigation.UniGuideNavGraph
import com.uniguide.app.ui.splash.SplashScreen
import com.uniguide.app.navigation.UniGuideNavGraph


class StudentActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MaterialTheme {

                var showSplash by remember {
                    mutableStateOf(true)
                }

                if (showSplash) {

                    SplashScreen(
                        onSplashFinished = {
                            showSplash = false
                        }
                    )

                } else {

                    UniGuideNavGraph()
                }
            MaterialTheme {
                UniGuideNavGraph()
            }
        }
    }
}