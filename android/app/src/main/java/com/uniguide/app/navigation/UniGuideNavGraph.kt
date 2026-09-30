package com.uniguide.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.uniguide.app.ui.academics.AcademicsScreen
import com.uniguide.app.ui.activities.ActivitiesScreen
import com.uniguide.app.ui.campusmap.CampusMapScreen
import com.uniguide.app.ui.emergency.EmergencyScreen
import com.uniguide.app.ui.events.EventsScreen
import com.uniguide.app.ui.facilities.FacilitiesScreen
import com.uniguide.app.ui.home.HomeScreen
import com.uniguide.app.ui.chat.ChatScreen
import com.uniguide.app.ui.hostel.HostelScreen
import com.uniguide.app.ui.splash.SplashScreen

import com.uniguide.app.ui.teacher.academiccalendar.TeacherAcademicCalendarScreen
import com.uniguide.app.ui.teacher.academicsystem.TeacherAcademicSystemScreen
import com.uniguide.app.ui.teacher.dashboard.TeacherDashboardScreen
import com.uniguide.app.ui.teacher.department.DepartmentScreen
import com.uniguide.app.ui.teacher.department.SchoolDetailScreen
import com.uniguide.app.ui.teacher.facultyguidelines.TeacherFacultyGuidelinesScreen
import com.uniguide.app.ui.teacher.notices.TeacherNoticesScreen
import com.uniguide.app.ui.teacher.profile.TeacherProfileScreen
import com.uniguide.app.ui.teacher.resources.TeacherResourcesScreen
import com.uniguide.app.ui.teacher.schedule.TeacherScheduleScreen

import com.uniguide.app.ui.university.UniversityScreen
import com.uniguide.app.ui.welcome.WelcomeScreen


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

    const val STUDENT_CHAT = "student_chat"


    // --------------------------------------------------------
    // Teacher
    // --------------------------------------------------------

    const val TEACHER_DASHBOARD = "teacher_dashboard"

    const val TEACHER_PROFILE = "teacher_profile"

    const val TEACHER_SCHEDULE = "teacher_schedule"

    const val TEACHER_NOTICES = "teacher_notices"

    const val TEACHER_DEPARTMENT = "teacher_department"

    const val TEACHER_RESOURCES = "teacher_resources"

    const val TEACHER_SCHOOL_DETAIL = "teacher_school_detail"

    const val TEACHER_ACADEMIC_SYSTEM = "teacher_academic_system"

    const val TEACHER_ACADEMIC_CALENDAR = "teacher_academic_calendar"

    const val TEACHER_FACULTY_GUIDELINES = "teacher_faculty_guidelines"

    const val TEACHER_CHAT = "teacher_chat"
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
                // CAMPUS MAP
                // --------------------------------------------

                onCampusMapClick = {

                    navController.navigate(
                        Routes.CAMPUS_MAP
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

                        "Emergency Contacts" -> {

                            navController.navigate(
                                Routes.EMERGENCY
                            )
                        }
                    }
                },

                onAiClick = {

                    navController.navigate(
                        Routes.STUDENT_CHAT
                    )
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
        // STUDENT AI CHAT
        // ====================================================

        composable(Routes.STUDENT_CHAT) {

            ChatScreen(
                userRole = "student"
            )
        }


        // ====================================================
        // TEACHER DASHBOARD
        // ====================================================

        composable(Routes.TEACHER_DASHBOARD) {

            TeacherDashboardScreen(

                onItemClick = { item ->

                    when (item) {

                        // ------------------------------------
                        // UNIVERSITY INFORMATION
                        // ------------------------------------

                        "University information" -> {

                            navController.navigate(
                                Routes.UNIVERSITY
                            )
                        }


                        // ------------------------------------
                        // DEPARTMENTS
                        // ------------------------------------

                        "Departments" -> {

                            navController.navigate(
                                Routes.TEACHER_DEPARTMENT
                            )
                        }


                        // ------------------------------------
                        // ACADEMIC SYSTEM
                        // ------------------------------------

                        "Academic System " -> {

                            navController.navigate(
                                Routes.TEACHER_ACADEMIC_SYSTEM
                            )
                        }


                        // ------------------------------------
                        // ACADEMIC CALENDAR
                        // ------------------------------------

                        "Academic Calendar" -> {

                            navController.navigate(
                                Routes.TEACHER_ACADEMIC_CALENDAR
                            )
                        }


                        // ------------------------------------
                        // FACULTY GUIDELINES
                        // ------------------------------------

                        "Faculty Guidelines" -> {

                            navController.navigate(
                                Routes.TEACHER_FACULTY_GUIDELINES
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
                        // EMERGENCY & SUPPORT
                        // ------------------------------------

                        "Emergency & Support " -> {

                            navController.navigate(
                                Routes.EMERGENCY
                            )
                        }
                    }
                },

                onAiClick = {

                    navController.navigate(
                        Routes.TEACHER_CHAT
                    )
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

            DepartmentScreen(

                onSchoolClick = { schoolId ->

                    navController.navigate(
                        "${Routes.TEACHER_SCHOOL_DETAIL}/$schoolId"
                    )
                }
            )
        }


        // ====================================================
        // TEACHER SCHOOL DETAIL
        // ====================================================

        composable(
            route = "${Routes.TEACHER_SCHOOL_DETAIL}/{schoolId}"
        ) { backStackEntry ->

            val schoolId = backStackEntry.arguments
                ?.getString("schoolId")
                ?: ""

            SchoolDetailScreen(
                schoolId = schoolId
            )
        }


        // ====================================================
        // TEACHER ACADEMIC SYSTEM
        // ====================================================

        composable(Routes.TEACHER_ACADEMIC_SYSTEM) {

            TeacherAcademicSystemScreen()
        }


        // ====================================================
        // TEACHER ACADEMIC CALENDAR
        // ====================================================

        composable(Routes.TEACHER_ACADEMIC_CALENDAR) {

            TeacherAcademicCalendarScreen()
        }


        // ====================================================
        // TEACHER FACULTY GUIDELINES
        // ====================================================

        composable(Routes.TEACHER_FACULTY_GUIDELINES) {

            TeacherFacultyGuidelinesScreen()
        }


        // ====================================================
        // TEACHER RESOURCES
        // ====================================================

        composable(Routes.TEACHER_RESOURCES) {

            TeacherResourcesScreen()
        }


        // ====================================================
        // TEACHER AI CHAT
        // ====================================================

        composable(Routes.TEACHER_CHAT) {

            ChatScreen(
                userRole = "teacher"
            )
        }

    }
}