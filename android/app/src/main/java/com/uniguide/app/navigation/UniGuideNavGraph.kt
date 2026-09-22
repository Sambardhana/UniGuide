package com.uniguide.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uniguide.app.ui.home.HomeScreen
import com.uniguide.app.ui.splash.SplashScreen
import com.uniguide.app.ui.welcome.WelcomeScreen

object UniGuideRoutes {
    const val SPLASH = "splash"
    const val WELCOME = "welcome"
    const val HOME = "home"
}

@Composable
fun UniGuideNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = UniGuideRoutes.SPLASH
    ) {

        // Splash Screen
        composable(UniGuideRoutes.SPLASH) {

            SplashScreen(
                onSplashFinished = {

                    navController.navigate(
                        UniGuideRoutes.WELCOME
                    ) {
                        popUpTo(UniGuideRoutes.SPLASH) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Welcome Screen
        composable(UniGuideRoutes.WELCOME) {

            WelcomeScreen(

                onStudentClick = {
                    navController.navigate(
                        UniGuideRoutes.HOME
                    )
                },

                onTeacherClick = {
                    // Teacher section will be connected later.
                },

                onGetStartedClick = {
                    navController.navigate(
                        UniGuideRoutes.HOME
                    )
                }
            )
        }

        // Student Home Screen
        composable(UniGuideRoutes.HOME) {

            HomeScreen(){

            }
        }
    }
}