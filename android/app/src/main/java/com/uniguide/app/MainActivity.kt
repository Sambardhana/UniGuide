package com.uniguide.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.uniguide.app.navigation.UniGuideNavGraph

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UniGuideApp()
        }
    }
}

@Composable
fun UniGuideApp() {
    UniGuideNavGraph()
}

