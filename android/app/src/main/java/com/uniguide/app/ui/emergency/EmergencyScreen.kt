package com.uniguide.app.ui.emergency

import androidx.compose.runtime.Composable
import com.uniguide.app.ui.common.StylishInfoScreen
import com.uniguide.app.ui.common.StylishItem

@Composable
fun EmergencyScreen() {

    StylishInfoScreen(

        title = "Emergency",

        subtitle = "Important help & safety contacts",

        headerIcon = "🚨",

        items = listOf(

            StylishItem(
                "🚨",
                "Emergency Services",
                "112",
                details = "India's unified emergency number for police, fire, ambulance and other emergency assistance."
            ),

            StylishItem(
                "🚓",
                "Police",
                "100",
                details = "Contact the police in case of a serious safety or security emergency."
            ),

            StylishItem(
                "🚑",
                "Ambulance",
                "108",
                details = "Emergency ambulance service for urgent medical situations."
            ),

            StylishItem(
                "🔥",
                "Fire & Rescue",
                "101",
                details = "Contact fire and rescue services in case of fire or related emergencies."
            ),

            StylishItem(
                "🏫",
                "CUTM Bhubaneswar",
                "+91 82600 77222",
                details = "Official university contact number for the Bhubaneswar campus."
            ),

            StylishItem(
                "🛡️",
                "Campus Security",
                "Contact Campus Security",
                details = "For immediate safety concerns, suspicious activity or security-related problems, contact the campus security team."
            ),

            StylishItem(
                "🩺",
                "Medical Support",
                "Campus Medical Facility",
                details = "For health-related emergencies, contact the campus medical facility or call an emergency ambulance."
            ),

            StylishItem(
                "📞",
                "Student Grievance",
                "+91 9437280622",
                details = "Published CUTM student grievance support contact."
            ),

            StylishItem(
                "📍",
                "Campus Address",
                "Bhubaneswar Campus",
                details = "Ramchandrapur, P.O. Jatni, Bhubaneswar, Khurda, Odisha – 752050."
            )
        ))
}
