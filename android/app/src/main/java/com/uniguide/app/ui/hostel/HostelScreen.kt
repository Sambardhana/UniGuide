package com.uniguide.app.ui.hostel

import androidx.compose.runtime.Composable
import com.uniguide.app.ui.common.StylishInfoScreen
import com.uniguide.app.ui.common.StylishItem

@Composable
fun HostelScreen() {
    StylishInfoScreen(
        title = "Hostel",
        subtitle = "Student residential facilities",
        headerIcon = "🏠",
        items = listOf(
            StylishItem(
                "🏠",
                "Hostel Accommodation",
                "Residential accommodation for students",
                details = "CUTM provides residential accommodation for students. Separate accommodation facilities are available for female students."
            ),
            StylishItem(
                "🛏️",
                "Student Living",
                "Comfortable residential environment",
                details = "Hostel accommodation provides students with a residential environment with supervision and support."
            ),
            StylishItem(
                "⚡",
                "24-Hour Power",
                "Power supply for residential facilities",
                details = "Residential facilities include 24-hour power supply to support students' academic and daily residential needs."
            ),
            StylishItem(
                "📶",
                "High-Speed Wi-Fi",
                "Campus connectivity",
                details = "Wi-Fi facilities help students access online learning resources, academic materials and internet-based services."
            ),
            StylishItem(
                "🩺",
                "Medical Facilities",
                "Healthcare support",
                details = "Medical facilities are available for students as part of the campus and residential support facilities."
            ),
            StylishItem(
                "🛡️",
                "Security",
                "Safety and vigilance",
                details = "Security and vigilance are maintained across the residential facilities to support student safety."
            ),
            StylishItem(
                "🏧",
                "ATM Facility",
                "Convenience facility on campus",
                details = "An ATM facility is available on campus for the convenience of students and residents."
            )
        )
    )
}
