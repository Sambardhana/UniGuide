package com.uniguide.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// ============================================================
// STUDENT SCREENS
// ============================================================

import com.uniguide.app.ui.academics.AcademicsScreen
import com.uniguide.app.ui.activities.ActivitiesScreen
import com.uniguide.app.ui.campusmap.CampusMapScreen
import com.uniguide.app.ui.emergency.EmergencyScreen
import com.uniguide.app.ui.events.EventsScreen
import com.uniguide.app.ui.facilities.FacilitiesScreen
import com.uniguide.app.ui.home.HomeScreen
import com.uniguide.app.ui.hostel.HostelScreen
import com.uniguide.app.ui.splash.SplashScreen
import com.uniguide.app.ui.university.UniversityScreen
import com.uniguide.app.ui.welcome.WelcomeScreen

// ============================================================
// TEACHER SCREENS
// ============================================================

import com.uniguide.app.ui.teacher.dashboard.TeacherDashboardScreen
import com.uniguide.app.ui.teacher.department.DepartmentScreen
import com.uniguide.app.ui.teacher.notices.TeacherNoticesScreen
import com.uniguide.app.ui.teacher.profile.TeacherProfileScreen
import com.uniguide.app.ui.teacher.resources.TeacherResourcesScreen
import com.uniguide.app.ui.teacher.schedule.TeacherScheduleScreen


// ============================================================
// ROUTES
// ============================================================

object Routes {

    // --------------------------------------------------------
    // Common
    // --------------------------------------------------------

    const val SPLASH = "splash"

    const val WELCOME = "welcome"


    // --------------------------------------------------------
    // Student
    // --------------------------------------------------------

    const val HOME = "home"

    const val UNIVERSITY = "university"

    const val ACADEMICS = "academics"

    const val HOSTEL = "hostel"

    const val FACILITIES = "facilities"

    const val ACTIVITIES = "activities"

    const val EVENTS = "events"

    const val CAMPUS_MAP = "campus_map"

    const val EMERGENCY = "emergency"


    // --------------------------------------------------------
    // Teacher
    // --------------------------------------------------------

    const val TEACHER_DASHBOARD = "teacher_dashboard"

    const val TEACHER_PROFILE = "teacher_profile"

    const val TEACHER_SCHEDULE = "teacher_schedule"

    const val TEACHER_NOTICES = "teacher_notices"

    const val TEACHER_DEPARTMENT = "teacher_department"

    const val TEACHER_RESOURCES = "teacher_resources"
}


// ============================================================
// NAVIGATION GRAPH
// ============================================================

@Composable
fun UniGuideNavGraph() {

    val navController = rememberNavController()


    NavHost(

        navController = navController,

        startDestination = Routes.SPLASH
    ) {


        // ====================================================
        // SPLASH
        // ====================================================

        composable(Routes.SPLASH) {

            SplashScreen(

                onSplashFinished = {

                    navController.navigate(
                        Routes.WELCOME
                    ) {

                        popUpTo(
                            Routes.SPLASH
                        ) {

                            inclusive = true
                        }
                    }
                }
            )
        }


        // ====================================================
        // WELCOME
        // ====================================================

        composable(Routes.WELCOME) {

            WelcomeScreen(

                // --------------------------------------------
                // STUDENT
                // --------------------------------------------

                onStudentClick = {

                    navController.navigate(
                        Routes.HOME
                    )
                },


                // --------------------------------------------
                // TEACHER
                // --------------------------------------------

                onTeacherClick = {

                    navController.navigate(
                        Routes.TEACHER_DASHBOARD
                    )
                },


                // --------------------------------------------
                // GET STARTED
                // --------------------------------------------

                onGetStartedClick = {

                    navController.navigate(
                        Routes.HOME
                    )
                }
            )
        }


        // ====================================================
        // STUDENT HOME
        // ====================================================

        composable(Routes.HOME) {

            HomeScreen(

                onItemClick = { item ->

                    when (item) {

                        "University" -> {

                            navController.navigate(
                                Routes.UNIVERSITY
                            )
                        }


                        "Academics" -> {

                            navController.navigate(
                                Routes.ACADEMICS
                            )
                        }


                        "Hostel" -> {

                            navController.navigate(
                                Routes.HOSTEL
                            )
                        }


                        "Facilities" -> {

                            navController.navigate(
                                Routes.FACILITIES
                            )
                        }


                        "Activities" -> {

                            navController.navigate(
                                Routes.ACTIVITIES
                            )
                        }


                        "Events" -> {

                            navController.navigate(
                                Routes.EVENTS
                            )
                        }


                        "Campus Map" -> {

                            navController.navigate(
                                Routes.CAMPUS_MAP
                            )
                        }


                        "Emergency Contacts" -> {

                            navController.navigate(
                                Routes.EMERGENCY
                            )
                        }
                    }
                }
            )
        }


        // ====================================================
        // STUDENT UNIVERSITY
        // ====================================================

        composable(Routes.UNIVERSITY) {

            UniversityScreen()
        }


        // ====================================================
        // STUDENT ACADEMICS
        // ====================================================

        composable(Routes.ACADEMICS) {

            AcademicsScreen()
        }


        // ====================================================
        // STUDENT HOSTEL
        // ====================================================

        composable(Routes.HOSTEL) {

            HostelScreen()
        }


        // ====================================================
        // STUDENT FACILITIES
        // ====================================================

        composable(Routes.FACILITIES) {

            FacilitiesScreen()
        }


        // ====================================================
        // STUDENT ACTIVITIES
        // ====================================================

        composable(Routes.ACTIVITIES) {

            ActivitiesScreen()
        }


        // ====================================================
        // STUDENT EVENTS
        // ====================================================

        composable(Routes.EVENTS) {

            EventsScreen()
        }


        // ====================================================
        // STUDENT CAMPUS MAP
        // ====================================================

        composable(Routes.CAMPUS_MAP) {

            CampusMapScreen()
        }


        // ====================================================
        // STUDENT EMERGENCY
        // ====================================================

        composable(Routes.EMERGENCY) {

            EmergencyScreen()
        }


        // ====================================================
        // TEACHER DASHBOARD
        // ====================================================

        composable(Routes.TEACHER_DASHBOARD) {

            TeacherDashboardScreen(

                onItemClick = { item ->

                    when (item) {


                        // ------------------------------------
                        // PROFILE
                        // ------------------------------------

                        "Profile" -> {

                            navController.navigate(
                                Routes.TEACHER_PROFILE
                            )
                        }


                        // ------------------------------------
                        // SCHEDULE
                        // ------------------------------------

                        "Schedule" -> {

                            navController.navigate(
                                Routes.TEACHER_SCHEDULE
                            )
                        }


                        // ------------------------------------
                        // NOTICES
                        // ------------------------------------

                        "Notices" -> {

                            navController.navigate(
                                Routes.TEACHER_NOTICES
                            )
                        }


                        // ------------------------------------
                        // DEPARTMENT
                        // ------------------------------------

                        "Department" -> {

                            navController.navigate(
                                Routes.TEACHER_DEPARTMENT
                            )
                        }


                        // ------------------------------------
                        // RESOURCES
                        // ------------------------------------

                        "Resources" -> {

                            navController.navigate(
                                Routes.TEACHER_RESOURCES
                            )
                        }
                    }
                }
            )
        }


        // ====================================================
        // TEACHER PROFILE
        // ====================================================

        composable(Routes.TEACHER_PROFILE) {

            TeacherProfileScreen()
        }


        // ====================================================
        // TEACHER SCHEDULE
        // ====================================================

        composable(Routes.TEACHER_SCHEDULE) {

            TeacherScheduleScreen()
        }


        // ====================================================
        // TEACHER NOTICES
        // ====================================================

        composable(Routes.TEACHER_NOTICES) {

            TeacherNoticesScreen()
        }


        // ====================================================
        // TEACHER DEPARTMENT
        // ====================================================

        composable(Routes.TEACHER_DEPARTMENT) {

            DepartmentScreen()
        }


        // ====================================================
        // TEACHER RESOURCES
        // ====================================================

        composable(Routes.TEACHER_RESOURCES) {

            TeacherResourcesScreen()
        }
    }
}