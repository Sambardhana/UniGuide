package com.uniguide.app.ui.events

import androidx.compose.runtime.Composable
import com.uniguide.app.ui.common.StylishInfoScreen
import com.uniguide.app.ui.common.StylishItem

@Composable
fun EventsScreen() {

    StylishInfoScreen(

        title = "Events",

        subtitle = "CUTM events & activities",

        headerIcon = "📅",

        items = listOf(

            StylishItem(
                "🔬",
                "Forensic Science Week 2026",
                "25–26 September 2026",
                "25–26 September 2026",
                "The School of Forensic Sciences event includes lectures, crime-scene reconstruction activities, poster presentation, short-video challenge and a cyber-forensics quiz."
            ),

            StylishItem(
                "💻",
                "E-Flair 2026",
                "May 2026",
                "May 2026",
                "An academic event associated with Electronics and Communication Engineering and Electrical & Electronics Engineering."
            ),

            StylishItem(
                "🏭",
                "Industry Expert Talk",
                "May 2026",
                "May 2026",
                "An industry expert session focused on next-generation manufacturing and professional learning."
            ),

            StylishItem(
                "🌍",
                "GIS Day 2026",
                "2026",
                "2026",
                "GIS Day is included in the university event calendar and focuses on Geographic Information Systems and related learning."
            ),

            StylishItem(
                "🤖",
                "AI & Machine Learning FDP",
                "1–10 October 2026",
                "1–10 October 2026",
                "A Faculty Development Programme on AI and Machine Learning applications in core engineering."
            ),

            StylishItem(
                "🎉",
                "Gajajyoti",
                "11–13 February 2026",
                "11–13 February 2026",
                "A university event featuring technology, management, science, literary, skill and cultural activities."
            ),

            StylishItem(
                "🏆",
                "University Sports",
                "2026",
                "2026",
                "Inter-university and intra-university sports activities provide opportunities for students to participate in competitive sports."
            ),

            StylishItem(
                "📚",
                "Academic Events",
                "Throughout the year",
                "Throughout the year",
                "CUTM conducts seminars, workshops, conferences, expert talks, competitions and other student-focused programmes."
            )
        ))
}
