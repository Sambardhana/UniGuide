package com.uniguide.app.ui.university

import androidx.compose.runtime.Composable
import com.uniguide.app.ui.common.StylishInfoScreen
import com.uniguide.app.ui.common.StylishItem

@Composable
fun UniversityScreen() {

    StylishInfoScreen(

        title = "University",

        subtitle = "Centurion University of Technology and Management",

        headerIcon = "🏛️",

        items = listOf(

            StylishItem(
                icon = "🏛️",
                title = "About CUTM",
                subtitle = "Centurion University of Technology and Management",
                details = "Centurion University of Technology and Management (CUTM) is a skill-integrated university. The Bhubaneswar campus is located at Ramachandrapur, Jatni."
            ),

            StylishItem(
                icon = "📍",
                title = "Bhubaneswar Campus",
                subtitle = "Ramachandrapur, P.O. Jatni",
                details = "The Bhubaneswar campus is located at Ramachandrapur, P.O. Jatni, Bhubaneswar, Khurda, Odisha – 752050. The campus is spread across about 40 acres."
            ),

            StylishItem(
                icon = "🎓",
                title = "Academic Schools",
                subtitle = "Multiple schools and departments",
                details = "The university has academic areas including Engineering & Technology, Management, Applied Sciences, Pharmacy & Life Sciences, Media & Communication, Healthcare, Forensic Sciences, Biotechnology, Law, Nursing, Design and Maritime Studies."
            ),

            StylishItem(
                icon = "💻",
                title = "Learning Environment",
                subtitle = "Modern academic facilities",
                details = "Students have access to libraries, laboratories, lecture theatres, Wi-Fi, conference spaces and other learning facilities."
            ),

            StylishItem(
                icon = "🏃",
                title = "Student Life",
                subtitle = "Sports, clubs and cultural activities",
                details = "Students can participate in sports, fitness activities, cultural programmes, clubs, competitions and other campus activities."
            ),

            StylishItem(
                icon = "🌱",
                title = "Green Campus",
                subtitle = "Sustainability initiatives",
                details = "The campus includes sustainability initiatives such as rainwater harvesting, solar energy, e-vehicles and green facilities."
            ),

            StylishItem(
                icon = "📞",
                title = "University Contact",
                subtitle = "+91 82600 77222",
                details = "Bhubaneswar Campus: Ramchandrapur, P.O. Jatni, Bhubaneswar, Khurda, Odisha – 752050."
            )
        )
    )
}