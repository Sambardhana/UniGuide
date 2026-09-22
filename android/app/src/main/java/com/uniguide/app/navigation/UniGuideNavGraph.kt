//package com.uniguide.app.ui.navigation

package com.uniguide.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uniguide.app.ui.home.HomeScreen
import com.uniguide.app.ui.welcome.WelcomeScreen

object Routes {
    const val WELCOME = "welcome"
    const val HOME = "home"
}

@Composable
fun UniGuideNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.WELCOME
    ) {

        composable(Routes.WELCOME) {
            WelcomeScreen(
                onStudentClick = {
                    navController.navigate(Routes.HOME)
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(){

            }
        }
    }
}