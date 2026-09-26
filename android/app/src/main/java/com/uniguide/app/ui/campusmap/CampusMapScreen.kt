package com.uniguide.app.ui.campusmap

import androidx.compose.runtime.Composable
import com.uniguide.app.ui.common.StylishInfoScreen
import com.uniguide.app.ui.common.StylishItem

@Composable
fun CampusMapScreen() {

    StylishInfoScreen(

        title = "Campus Map",

        subtitle = "Important locations at CUTM",

        headerIcon = "📍",

        items = listOf(

            StylishItem(
                "🏛️",
                "Academic Buildings",
                "Classrooms and academic blocks",
                details = "Academic buildings contain classrooms, laboratories and spaces used for lectures and practical sessions."
            ),

            StylishItem(
                "📚",
                "Central Library",
                "Books and digital resources",
                details = "The library provides students with books, reference materials and digital learning resources."
            ),

            StylishItem(
                "🏠",
                "Hostel Area",
                "Student residential area",
                details = "Residential facilities are available for students, including separate accommodation facilities for female students."
            ),

            StylishItem(
                "🏟️",
                "Sports Complex",
                "Sports and fitness",
                details = "Sports areas support basketball, volleyball, cricket, football, swimming and other sports."
            ),

            StylishItem(
                "🍴",
                "Cafeteria",
                "Food and convenience",
                details = "Students can access campus food and convenience facilities including cafeteria and market areas."
            ),

            StylishItem(
                "🩺",
                "Medical Facility",
                "Healthcare support",
                details = "Medical facilities are available on campus to support student health needs."
            ),

            StylishItem(
                "🏢",
                "Auditorium",
                "Events and programmes",
                details = "The auditorium and event spaces are used for seminars, workshops, cultural programmes and university events."
            ),

            StylishItem(
                "🏋️",
                "Gym & Fitness Centre",
                "Fitness activities",
                details = "Fitness facilities are available for students interested in exercise and physical fitness."
            ),

            StylishItem(
                "🚌",
                "Transport Area",
                "University transport",
                details = "University transport facilities support movement between the campus and other locations."
            ),

            StylishItem(
                "📍",
                "Bhubaneswar Campus",
                "Ramachandrapur, Jatni",
                details = "Centurion University of Technology and Management, Ramachandrapur, P.O. Jatni, Bhubaneswar, Khurda, Odisha – 752050."
            )
        )
    )
}