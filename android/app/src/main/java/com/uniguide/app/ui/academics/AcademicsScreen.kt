package com.uniguide.app.ui.academics

import androidx.compose.runtime.Composable
import com.uniguide.app.ui.common.StylishInfoScreen
import com.uniguide.app.ui.common.StylishItem

@Composable
fun AcademicsScreen() {

    StylishInfoScreen(

        title = "Academics",

        subtitle = "Courses, departments and learning",

        headerIcon = "🎓",

        items = listOf(StylishItem(
                "🎓",
                "Programs Offered",
                "B.Tech, M.Tech, MBA, BBA, BCA, MCA, B.Sc, M.Sc and more",
                details = "CUTM offers various undergraduate, postgraduate and other academic programmes including Engineering, Management, Computer Applications, Sciences, Pharmacy, Nursing, Law and Design."
            ), StylishItem(
                "📚",
                "Departments",
                "Different academic schools and departments",
                details = "Academic areas include Engineering, Management, Applied Sciences, Pharmacy, Healthcare, Law, Design, Maritime Studies and other disciplines."
            ), StylishItem(
                "🧪",
                "Laboratories",
                "Practical and technical learning",
                details = "Students can use laboratories for practical sessions, experiments, technical learning and project work."
            ), StylishItem(
                "📖",
                "Library",
                "Books and digital learning resources",
                details = "The library provides academic books, reference materials, journals and digital learning resources for students."
            ), StylishItem(
                "💻",
                "Smart Learning",
                "Digital learning facilities",
                details = "Classrooms and learning spaces support digital learning, presentations, internet access and academic activities."
            ), StylishItem(
                "📝",
                "Examinations",
                "Internal and semester examinations",
                details = "The university conducts internal assessments, practical examinations and semester examinations according to the academic schedule."
            ), StylishItem(
                "🏆",
                "Skill Development",
                "Practical and industry-oriented learning",
                details = "Students can develop practical skills through projects, internships, workshops, training and industry-oriented learning."
            )
        )
    )
}