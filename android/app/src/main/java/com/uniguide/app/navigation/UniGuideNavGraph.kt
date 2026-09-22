package com.uniguide.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uniguide.app.ui.home.HomeScreen
import com.uniguide.app.ui.academics.AcademicsScreen
import com.uniguide.app.ui.activities.ActivitiesScreen
import com.uniguide.app.ui.campusmap.CampusMapScreen
import com.uniguide.app.ui.emergency.EmergencyScreen
import com.uniguide.app.ui.events.EventsScreen
import com.uniguide.app.ui.facilities.FacilitiesScreen
import com.uniguide.app.ui.home.HomeScreen
import com.uniguide.app.ui.hostel.HostelScreen
import com.uniguide.app.ui.university.UniversityScreen
import com.uniguide.app.ui.welcome.WelcomeScreen

object Routes {
    const val WELCOME = "welcome"
    const val HOME = "home"
    const val UNIVERSITY = "university"
    const val ACADEMICS = "academics"
    const val HOSTEL = "hostel"
    const val FACILITIES = "facilities"
    const val ACTIVITIES = "activities"
    const val EVENTS = "events"
    const val CAMPUS_MAP = "campus_map"
    const val EMERGENCY = "emergency"
}

@Composable
fun UniGuideNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.WELCOME
    ) {

        // Welcome Screen
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
        // Student Home / Dashboard
        composable(Routes.HOME) {
            HomeScreen(
                onItemClick = { item ->

                    when (item) {

                        "University" -> {
                            navController.navigate(Routes.UNIVERSITY)
                        }

                        "Academics" -> {
                            navController.navigate(Routes.ACADEMICS)
                        }

                        "Hostel" -> {
                            navController.navigate(Routes.HOSTEL)
                        }

                        "Facilities" -> {
                            navController.navigate(Routes.FACILITIES)
                        }

                        "Activities" -> {
                            navController.navigate(Routes.ACTIVITIES)
                        }

                        "Events" -> {
                            navController.navigate(Routes.EVENTS)
                        }

                        "Campus Map" -> {
                            navController.navigate(Routes.CAMPUS_MAP)
                        }

                        "Emergency Contacts" -> {
                            navController.navigate(Routes.EMERGENCY)
                        }
                    }
                }
            )
        }

        // University
        composable(Routes.UNIVERSITY) {
            UniversityScreen()
        }

        // Academics
        composable(Routes.ACADEMICS) {
            AcademicsScreen()
        }

        // Hostel
        composable(Routes.HOSTEL) {
            HostelScreen()
        }

        // Facilities
        composable(Routes.FACILITIES) {
            FacilitiesScreen()
        }

        // Activities
        composable(Routes.ACTIVITIES) {
            ActivitiesScreen()
        }

        // Events
        composable(Routes.EVENTS) {
            EventsScreen()
        }

        // Campus Map
        composable(Routes.CAMPUS_MAP) {
            CampusMapScreen()
        }

        // Emergency
        composable(Routes.EMERGENCY) {
            EmergencyScreen()
        }
    }
}