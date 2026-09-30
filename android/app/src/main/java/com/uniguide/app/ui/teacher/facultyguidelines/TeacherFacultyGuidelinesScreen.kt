package com.uniguide.app.ui.teacher.facultyguidelines

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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


private data class FacultyTopic(
    val icon: String,
    val title: String,
    val description: String
)


private val facultyTopics = listOf(

    FacultyTopic(
        icon = "👨‍🏫",
        title = "Faculty Duties & Responsibilities",
        description = """
            CUTM defines faculty responsibilities according to cadre and designation.
            Faculty members have both academic and non-academic responsibilities.
            Academic responsibilities include classroom and teaching-related work.
            Non-academic responsibilities also include professional self-development activities.
            Faculty roles may include teaching, mentoring and academic coordination.
            Faculty members may participate in examinations and academic activities.
            Programme and department responsibilities are assigned according to the role.
            Faculty may also contribute to research and institutional activities.
            Coordinators and departmental roles include additional academic responsibilities.
            Faculty members are expected to work according to university procedures.
            Specific responsibilities may also depend on the school or department.
        """.trimIndent()
    ),

    FacultyTopic(
        icon = "📋",
        title = "Code of Conduct",
        description = """
            CUTM's Faculty & Staff Handbook includes a dedicated Faculty Code of Conduct.
            The handbook also includes the UGC Professional Code of Conduct for teachers.
            Faculty conduct is expected to support a professional academic environment.
            Teachers are expected to maintain appropriate professional behaviour.
            Faculty members should treat students and colleagues with respect.
            Academic responsibilities should be carried out with integrity and fairness.
            University rules and prescribed procedures are expected to be followed.
            The university also maintains policies against harassment and discrimination.
            Conduct that violates university policy may be subject to appropriate action.
            Faculty members should exercise professional judgement in their activities.
        """.trimIndent()
    ),

    FacultyTopic(
        icon = "🎓",
        title = "Academic Freedom & Equal Opportunity",
        description = """
            CUTM's handbook recognises academic freedom for faculty members.
            Faculty have the right to examine, question, teach, learn and investigate.
            Faculty members can share academic ideas while respecting others' opinions.
            Academic freedom is exercised with appropriate judgement and restraint.
            CUTM also states a policy of equal opportunity in employment.
            Employment decisions are intended to be based on qualifications and performance.
            Equal opportunity applies to recruitment, training, promotion and benefits.
            The university supports tolerance, respect and fair treatment.
            Concerns about violations can be reported to the appropriate authority.
            Complaints under the policy are intended to be investigated impartially.
        """.trimIndent()
    ),

    FacultyTopic(
        icon = "🕒",
        title = "Leave & Service Rules",
        description = """
            CUTM maintains leave and service provisions for faculty and staff.
            The Faculty & Staff Handbook includes a section on leave of absence.
            Leave rules are provided to the concerned employee at the time of joining.
            Faculty members are expected to consider academic responsibilities when planning leave.
            The handbook advises avoiding leave except on unavoidable grounds.
            Prior intimation should be given as far as practicable.
            Leave and absence procedures are connected with university administration.
            Service conditions are also covered within the HR policies and practices.
            New employees receive information and required documentation at joining.
            School and departmental procedures may provide additional instructions.
            Faculty should follow the applicable leave process for their position.
        """.trimIndent()
    ),

    FacultyTopic(
        icon = "📊",
        title = "Performance Appraisal",
        description = """
            CUTM conducts performance appraisal of faculty and staff members periodically.
            The appraisal reviews performance over a given period.
            It helps identify gaps between actual and expected performance.
            The process supports organisational and academic development.
            Appraisal can help identify training and development needs.
            Faculty Development Programmes may be used to address development needs.
            Appraisal information may support HR decisions such as promotion or transfer.
            The process also clarifies expectations and responsibilities.
            Faculty performance can influence increments and other benefits.
            The handbook describes both fixed and performance-linked components.
            Changes to the appraisal system are to be communicated to employees.
        """.trimIndent()
    ),

    FacultyTopic(
        icon = "🔬",
        title = "Research & Development",
        description = """
            CUTM maintains a Research and Development Policy for research activities.
            The research policy aims to support high-impact and context-specific applied research.
            Research activities are intended to generate knowledge and strengthen learning.
            Faculty are encouraged to publish research work in appropriate journals.
            Research outputs may include working papers and research studies.
            Publications are subject to the university's quality processes.
            Faculty research can contribute to curriculum development and experiential learning.
            Research activities may cover focused areas identified by the university.
            CUTM also provides an incentive framework for research involvement.
            Research, consultancy, publications and patents are part of this broader framework.
            Faculty members are encouraged to develop research and innovation activities.
        """.trimIndent()
    ),

    FacultyTopic(
        icon = "🏆",
        title = "Incentives, Training & Development",
        description = """
            CUTM maintains faculty incentive and professional development provisions.
            The university's current Rules & Policies page lists a Faculty Incentives Policy 2025.
            The handbook also describes an incentive scheme for faculty participation.
            Incentives are intended to encourage research, consultancy and field projects.
            Faculty are encouraged to develop publications and patents.
            The handbook provides for faculty development and in-service training.
            Faculty may undertake training or industry exposure under applicable provisions.
            Summer research fellowships are also encouraged in the handbook.
            Faculty can participate in approved national and international conferences.
            The handbook describes financial and infrastructure support for eligible conferences.
            Professional development is connected with continuous improvement of faculty work.
        """.trimIndent()
    ),

    FacultyTopic(
        icon = "🤝",
        title = "Grievance Redressal & Workplace Policies",
        description = """
            CUTM's Faculty & Staff Handbook includes a Faculty Grievance Redressal System.
            The handbook states that the system addresses grievances and complaints of faculty and staff.
            CUTM also maintains policies concerning equal opportunity and harassment.
            The university states that discrimination and harassment are not tolerated.
            Faculty and staff can use the applicable grievance mechanisms for concerns.
            Complaints may be reported through the designated university authorities.
            The handbook also describes protection against retaliation for raising concerns.
            Faculty are expected to maintain a respectful academic and workplace environment.
            The current university Rules & Policies page lists related policies and committees.
            These include grievance, equal opportunity and anti-harassment provisions.
            School-specific procedures may apply where relevant.
        """.trimIndent()
    )
)


@Composable
fun TeacherFacultyGuidelinesScreen() {

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
                text = "📋",
                fontSize = 30.sp
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Faculty Guidelines",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = "Guidelines, responsibilities and policies for faculty",
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
                    text = "CUTM Faculty Information",
                    color = Color(0xFF07516A),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(7.dp)
                )

                Text(
                    text = "Important information about faculty responsibilities, conduct, professional development and university policies.",
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
            text = "Faculty Guidelines",

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

            items(facultyTopics) { topic ->

                FacultyTopicCard(
                    topic = topic
                )
            }
        }
    }
}


// ==========================================================
// FACULTY TOPIC CARD
// ==========================================================

@Composable
private fun FacultyTopicCard(
    topic: FacultyTopic
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

                Box(
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