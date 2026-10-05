package com.uniguide.app.ui.academics

import androidx.compose.runtime.Composable
import com.uniguide.app.ui.common.InfoSection
import com.uniguide.app.ui.common.StylishInfoScreen
import com.uniguide.app.ui.common.StylishItem

@Composable
fun AcademicsScreen(
    onDepartmentsClick: () -> Unit
) {

    StylishInfoScreen(
        title = "Academics",
        subtitle = "Courses, departments and learning",
        headerIcon = "🎓",

        items = listOf(

            // =====================================================
            // 1. DEPARTMENTS
            // =====================================================

            StylishItem(
                icon = "📚",
                title = "Departments",
                subtitle = "Explore academic schools and departments",
                details = """
Explore the different academic schools and departments available at Centurion University.

Students can select a department to explore information about the respective academic school.
                """.trimIndent()
            ),

            // =====================================================
            // 2. SKILL DEVELOPMENT
            // =====================================================

            StylishItem(
                icon = "🏆",
                title = "Skill Development",
                subtitle = "Practical, industry-oriented and career-focused learning",

                details = """
Skill development is an important part of student learning at Centurion University.

Students can develop practical, technical, professional and entrepreneurial skills alongside their regular academic studies.

The university's course repository includes skill-oriented learning opportunities covering technology, engineering, healthcare, agriculture, renewable energy, entrepreneurship, sports and other professional areas.
                """.trimIndent(),

                sections = listOf(

                    // -------------------------------------------------
                    // TECHNOLOGY
                    // -------------------------------------------------

                    InfoSection(
                        icon = "💻",
                        title = "Technology & Digital Skills",
                        description = "Develop practical knowledge in modern digital and emerging technologies.",
                        skills = listOf(
                            "Internet of Things",
                            "Drone Piloting",
                            "Blender & Unity",
                            "Desktop Publishing",
                            "Camera Operation",
                            "Video Editing",
                            "Quantum Computing",
                            "High-performance Computing"
                        )
                    ),

                    // -------------------------------------------------
                    // ENGINEERING
                    // -------------------------------------------------

                    InfoSection(
                        icon = "⚙️",
                        title = "Engineering & Manufacturing",
                        description = "Hands-on learning related to engineering systems, manufacturing and technical operations.",
                        skills = listOf(
                            "Fabrication",
                            "Precast Concrete Manufacturing",
                            "Hi-Tech Surveying",
                            "Mechatronics System Design",
                            "Transformer Maintenance",
                            "Electrical Installation",
                            "CCTV Installation",
                            "Home Appliance Maintenance",
                            "Refrigeration & Air Conditioning"
                        )
                    ),

                    // -------------------------------------------------
                    // HEALTHCARE
                    // -------------------------------------------------

                    InfoSection(
                        icon = "🏥",
                        title = "Healthcare Skills",
                        description = "Skill-oriented learning related to healthcare and medical support services.",
                        skills = listOf(
                            "Medical Laboratory Technology",
                            "Radiology Technology",
                            "X-ray Technology",
                            "Phlebotomy Technology",
                            "First Aid Service",
                            "Emergency Medical Technology",
                            "Operating Theatre Technology",
                            "General Duty Assistance"
                        )
                    ),

                    // -------------------------------------------------
                    // AGRICULTURE
                    // -------------------------------------------------

                    InfoSection(
                        icon = "🌱",
                        title = "Agriculture & Farming",
                        description = "Practical skills related to modern agriculture and sustainable farming.",
                        skills = listOf(
                            "Organic Farming",
                            "Mushroom Farming",
                            "Hydroponics Technology",
                            "Poultry Farming",
                            "Dairy Farming",
                            "Vermicomposting",
                            "Seed Production",
                            "Paddy Processing & Marketing"
                        )
                    ),

                    // -------------------------------------------------
                    // RENEWABLE ENERGY
                    // -------------------------------------------------

                    InfoSection(
                        icon = "☀️",
                        title = "Renewable Energy",
                        description = "Build practical knowledge in solar energy and sustainable energy systems.",
                        skills = listOf(
                            "Solar PV Installation",
                            "Solar Lighting Technology",
                            "Solar PV Microgrid System",
                            "Solar Equipment Assembly",
                            "Solar Thermal Engineering"
                        )
                    ),

                    // -------------------------------------------------
                    // BUSINESS
                    // -------------------------------------------------

                    InfoSection(
                        icon = "💼",
                        title = "Business & Entrepreneurship",
                        description = "Develop entrepreneurial thinking, business skills and workplace readiness.",
                        skills = listOf(
                            "Business Plan Preparation",
                            "Wantrepreneur to Entrepreneur",
                            "Retail Sales",
                            "Business Development",
                            "Entrepreneurial Skills"
                        )
                    ),

                    // -------------------------------------------------
                    // SPORTS
                    // -------------------------------------------------

                    InfoSection(
                        icon = "🏀",
                        title = "Sports, Fitness & Wellness",
                        description = "Opportunities to develop physical fitness, sports skills and personal wellbeing.",
                        skills = listOf(
                            "Basketball",
                            "Gym Fitness",
                            "Swimming",
                            "Yoga & Meditation",
                            "Fitness & Wellness"
                        )
                    ),

                    // -------------------------------------------------
                    // PROFESSIONAL SKILLS
                    // -------------------------------------------------

                    InfoSection(
                        icon = "🚀",
                        title = "Professional & Life Skills",
                        description = "Skills that help students become more confident and career-ready.",
                        skills = listOf(
                            "Communication",
                            "Teamwork",
                            "Problem Solving",
                            "Leadership",
                            "Presentation Skills",
                            "Workplace Readiness",
                            "Career Development"
                        )
                    )
                )
            ),

            // =====================================================
            // 3. LABORATORIES
            // =====================================================

            StylishItem(
                icon = "🧪",
                title = "Laboratories",
                subtitle = "Practical and technical learning",
                details = """
Students can use laboratories for practical sessions, experiments, technical learning and project work.

Laboratory-based learning helps students connect theoretical concepts with practical implementation and develop hands-on technical skills.
                """.trimIndent()
            ),

            // =====================================================
            // 4. LIBRARY
            // =====================================================

            StylishItem(
                icon = "📖",
                title = "Library",
                subtitle = "Books and digital learning resources",
                details = """
The university library provides students with academic books, reference materials, journals and digital learning resources.

Students can use these resources to support their studies, assignments, projects and research activities.
                """.trimIndent()
            ),

            // =====================================================
            // 5. SMART LEARNING
            // =====================================================

            StylishItem(
                icon = "💻",
                title = "Smart Learning",
                subtitle = "Digital learning facilities",
                details = """
Smart learning facilities support digital education, presentations, online resources and technology-enabled academic activities.

These facilities help students combine classroom learning with modern digital learning methods.
                """.trimIndent()
            ),

            // =====================================================
            // 6. EXAMINATIONS
            // =====================================================

            StylishItem(
                icon = "📝",
                title = "Examinations",
                subtitle = "Internal and semester examinations",
                details = """
The university conducts internal assessments, practical examinations and semester examinations according to the academic schedule.

Students are evaluated through different academic and practical assessment methods.
                """.trimIndent()
            )
        ),

        // =========================================================
        // CARD CLICK HANDLING
        // =========================================================

        onItemClick = { item ->

            if (item.title == "Departments") {

                onDepartmentsClick()

                true

            } else {

                false
            }
        }
    )
}