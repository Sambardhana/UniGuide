package com.uniguide.app.ui.activities

import androidx.compose.runtime.Composable
import com.uniguide.app.ui.common.StylishInfoScreen
import com.uniguide.app.ui.common.StylishItem

@Composable
fun ActivitiesScreen() {

    StylishInfoScreen(

        title = "Activities",

        subtitle = "Student clubs & campus activities",

        headerIcon = "🎭",

        items = listOf(

            StylishItem(
                "🎵",
                "Music Club",
                "Music and performances",
                details = "Students can participate in music activities, performances and cultural programmes."
            ),

            StylishItem(
                "💃",
                "Dance Club",
                "Dance and cultural performances",
                details = "Students can participate in dance practices, performances and cultural programmes."
            ),

            StylishItem(
                "🎭",
                "Drama Club",
                "Theatre and acting",
                details = "Students interested in acting, theatre and stage performances can participate in drama activities."
            ),

            StylishItem(
                "🎨",
                "Arts & Painting",
                "Creative activities",
                details = "Students can participate in painting, visual arts, handicrafts and other creative activities."
            ),

            StylishItem(
                "📖",
                "Literature Club",
                "Reading and creative expression",
                details = "Literary activities help students develop communication, reading and creative expression."
            ),

            StylishItem(
                "🌱",
                "Green Club",
                "Environment and sustainability",
                details = "Green activities encourage environmental awareness and sustainability among students."
            ),

            StylishItem(
                "🔬",
                "Science Club",
                "Science and innovation",
                details = "Students can participate in science-related learning, innovation and academic activities."
            ),

            StylishItem(
                "🧘",
                "Yoga & Fitness",
                "Health and wellness",
                details = "Yoga, gym and fitness activities encourage students to maintain physical health and wellness."
            ),

            StylishItem(
                "🏀",
                "Sports",
                "Sports and competitions",
                details = "Students can participate in basketball, kabaddi, swimming and other indoor and outdoor sports."
            ),

            StylishItem(
                "🤝",
                "Social Responsibility",
                "Community activities",
                details = "Students can participate in community service, awareness programmes and social responsibility activities."
            )
        ))
}
