package com.uniguide.app.ui.facilities

import androidx.compose.runtime.Composable
import com.uniguide.app.ui.common.StylishInfoScreen
import com.uniguide.app.ui.common.StylishItem

@Composable
fun FacilitiesScreen() {
    StylishInfoScreen(
        title = "Facilities",
        subtitle = "Campus facilities at CUTM",
        headerIcon = "🏢",
        items = listOf(
            StylishItem(
                "📚",
                "Library",
                "Academic and digital resources",
                details = "The campus library provides books, reference materials, periodicals and digital learning resources."
            ),
            StylishItem(
                "📶",
                "Wi-Fi",
                "Campus internet connectivity",
                details = "Wi-Fi facilities are available on campus to support academic work, online learning and digital resources."
            ),
            StylishItem(
                "🏛️",
                "Auditorium",
                "Events and academic programmes",
                details = "Auditorium and lecture spaces are used for seminars, workshops, conferences, cultural programmes and university events."
            ),
            StylishItem(
                "🏋️",
                "Gym & Fitness",
                "Health and fitness facilities",
                details = "Students can participate in fitness activities using gymnasium and open fitness facilities."
            ),
            StylishItem(
                "🏀",
                "Sports Facilities",
                "Indoor and outdoor sports",
                details = "Sports facilities include basketball, volleyball, cricket, football, swimming and other sports."
            ),
            StylishItem(
                "🍴",
                "Food & Cafeteria",
                "Food and convenience facilities",
                details = "Students can access campus food, mess, cafeteria and convenience facilities."
            ),
            StylishItem(
                "🩺",
                "Healthcare",
                "Medical support",
                details = "Healthcare facilities are available to support student medical needs."
            ),
            StylishItem(
                "🚌",
                "Transport",
                "University transportation",
                details = "University transport services support transportation requirements of students and staff."
            ),
            StylishItem(
                "🛡️",
                "Security",
                "Campus safety",
                details = "Security services and CCTV facilities support a safe campus environment."
            ),
            StylishItem(
                "🌱",
                "Green Campus",
                "Sustainability facilities",
                details = "The university lists sustainability initiatives including solar systems, rainwater harvesting, sewage treatment and green spaces."
            )
        )
    )
}
