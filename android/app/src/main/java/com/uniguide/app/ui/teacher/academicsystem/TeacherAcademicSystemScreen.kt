package com.uniguide.app.ui.teacher.academicsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


private data class AcademicTopic(
    val icon: String,
    val title: String,
    val description: String
)


private val academicTopics = listOf(

    AcademicTopic(
        icon = "📚",
        title = "Academic Regulations",
        description = """
            CUTM maintains academic regulations for different programmes and schools.
            These regulations define the academic structure and requirements of programmes.
            Programme regulations may differ according to the discipline and level of study.
            Regulations describe important academic procedures followed by the university.
            Course registration is carried out according to the courses offered for a semester.
            Students select courses from the available academic baskets where applicable.
            Programme structures may include major, minor, skill and multidisciplinary courses.
            Academic regulations also provide guidance on credits and programme progression.
            Rules related to examinations and eligibility are also included in programme regulations.
            Internships, projects and research components may form part of academic programmes.
            Some programmes provide multiple entry and exit options under the applicable structure.
            Students and faculty should refer to the applicable programme regulations for details.
        """.trimIndent()
    ),

    AcademicTopic(
        icon = "📅",
        title = "Academic Calendar",
        description = """
            CUTM publishes academic calendars for the academic session.
            The current 2026–27 calendar is organised according to schools and programmes.
            Different calendars are provided for different years and academic levels.
            Undergraduate and postgraduate programmes may have separate schedules.
            Some diploma and certificate programmes also have their own calendars.
            The calendar helps students and faculty follow the academic session.
            Important academic periods are planned according to the published schedule.
            Semester activities are organised according to the relevant programme calendar.
            The calendar may vary between schools because programmes have different structures.
            Faculty members should follow the calendar applicable to their school and programme.
            Students should regularly check the latest calendar issued by the university.
            The published calendar is the reference for planning academic activities.
        """.trimIndent()
    ),

    AcademicTopic(
        icon = "🎓",
        title = "Programs & Courses",
        description = """
            CUTM offers academic programmes across a wide range of disciplines.
            The university provides undergraduate, postgraduate, doctoral and other programmes.
            Different schools offer programmes based on their academic areas.
            Programmes may include engineering, sciences, management, agriculture and healthcare.
            Other areas include law, pharmacy, biotechnology, design and vocational education.
            Courses are organised as part of the structure of each academic programme.
            Students may have core or major courses within their chosen discipline.
            Some programmes also provide minor, skill and multidisciplinary learning options.
            Course registration is carried out from the courses available for the programme.
            Credit values are assigned to courses according to the programme structure.
            Programme duration and academic progression depend on the applicable regulations.
            Faculty members should follow the curriculum and course structure of their programme.
        """.trimIndent()
    ),

    AcademicTopic(
        icon = "🏫",
        title = "Academics at CUTM",
        description = """
            CUTM follows a multidisciplinary approach to academic education.
            Academic programmes are designed around practical and skill-oriented learning.
            The university combines classroom learning with hands-on academic activities.
            Different schools provide specialised education in their respective disciplines.
            Academic learning can include projects, internships and research activities.
            Programmes may also include interdisciplinary and multidisciplinary courses.
            CUTM promotes learning connected with industry and real-world applications.
            Research and innovation are included in several academic areas.
            Some schools provide opportunities for specialised and emerging fields.
            Academic structures are aligned with the requirements of individual programmes.
            Faculty members support students through teaching, projects and academic guidance.
            The overall academic structure is intended to support professional and higher-study pathways.
        """.trimIndent()
    ),

    AcademicTopic(
        icon = "💼",
        title = "Internship Policy",
        description = """
            Internships are used to provide students with practical exposure to real-world work.
            The internship policy defines the objectives and responsibilities of trainees.
            Students are expected to follow the duties and responsibilities assigned during training.
            Internship activities can connect academic learning with practical application.
            Students may work with industry, organisations or other approved environments.
            The policy includes guidance for trainee self-evaluation.
            Internship performance may be assessed as part of the academic process.
            Students may be required to maintain daily work records.
            Weekly reporting is also included in the internship process.
            General instructions guide professional behaviour during the internship.
            Internship activities can help students develop professional skills and experience.
            The internship structure may vary according to the programme and applicable regulations.
        """.trimIndent()
    )
)


@Composable
fun TeacherAcademicSystemScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFF3FBFD)
            )
    ) {

        // ==================================================
        // HEADER
        // ==================================================

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        bottomStart = 32.dp,
                        bottomEnd = 32.dp
                    )
                )
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF08758A),
                            Color(0xFF1498AA),
                            Color(0xFF71D0D5)
                        )
                    )
                )
                .padding(
                    start = 26.dp,
                    end = 26.dp,
                    top = 36.dp,
                    bottom = 28.dp
                )
        ) {

            Text(
                text = "📚",
                fontSize = 30.sp
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Academic System",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = "Academic information for teachers",
                color = Color.White.copy(
                    alpha = 0.9f
                ),
                fontSize = 14.sp
            )
        }


        Spacer(
            modifier = Modifier.height(16.dp)
        )


        // ==================================================
        // INTRODUCTION
        // ==================================================

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 22.dp
                ),

            shape = RoundedCornerShape(20.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = "CUTM Academic Information",
                    color = Color(0xFF07516A),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(7.dp)
                )

                Text(
                    text = "Read important information about academic regulations, calendars, programmes, academics and internships.",
                    color = Color(0xFF52676C),
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
            }
        }


        Spacer(
            modifier = Modifier.height(16.dp)
        )


        // ==================================================
        // SECTION TITLE
        // ==================================================

        Text(
            text = "Academic Topics",

            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 22.dp
                ),

            color = Color(0xFF07516A),

            fontSize = 19.sp,

            fontWeight = FontWeight.Bold
        )


        Spacer(
            modifier = Modifier.height(10.dp)
        )


        // ==================================================
        // TOPIC LIST
        // ==================================================

        LazyColumn(

            modifier = Modifier.fillMaxSize(),

            verticalArrangement = Arrangement.spacedBy(
                12.dp
            ),

            contentPadding = PaddingValues(
                start = 22.dp,
                end = 22.dp,
                bottom = 24.dp
            )
        ) {

            items(academicTopics) { topic ->

                AcademicTopicCard(
                    topic = topic
                )
            }
        }
    }
}


// ==========================================================
// ACADEMIC TOPIC CARD
// ==========================================================

@Composable
private fun AcademicTopicCard(
    topic: AcademicTopic
) {

    var expanded by remember {
        mutableStateOf(false)
    }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                expanded = !expanded
            },

        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            // --------------------------------------------
            // TOP ROW
            // --------------------------------------------

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),

                verticalAlignment = Alignment.CenterVertically
            ) {

                androidx.compose.foundation.layout.Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(
                            RoundedCornerShape(15.dp)
                        )
                        .background(
                            Color(0xFFE8F7F9)
                        ),

                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = topic.icon,
                        fontSize = 24.sp
                    )
                }


                Spacer(
                    modifier = Modifier.width(14.dp)
                )


                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = topic.title,
                        color = Color(0xFF07516A),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = if (expanded) {
                            "Tap to collapse"
                        } else {
                            "Tap to read information"
                        },
                        color = Color(0xFF64777B),
                        fontSize = 13.sp
                    )
                }


                Text(
                    text = if (expanded) {
                        "⌃"
                    } else {
                        "⌄"
                    },

                    color = Color(0xFF08758A),

                    fontSize = 25.sp,

                    fontWeight = FontWeight.Bold
                )
            }


            // --------------------------------------------
            // EXPANDED CONTENT
            // --------------------------------------------

            if (expanded) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 18.dp,
                            end = 18.dp,
                            bottom = 18.dp
                        )
                ) {

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = topic.description,
                        color = Color(0xFF465C61),
                        fontSize = 14.sp,
                        lineHeight = 21.sp
                    )
                }
            }
        }
    }
}