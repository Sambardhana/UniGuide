package com.uniguide.app.ui.teacher.notices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// ==========================================================
// TEACHER NOTICE MODEL
// ==========================================================

data class TeacherNotice(
    val title: String,
    val description: String,
    val date: String
)


// ==========================================================
// TEACHER NOTICES SCREEN
// ==========================================================

@Composable
fun TeacherNoticesScreen() {

    val notices = listOf(

        TeacherNotice(
            "Faculty Meeting",
            "Department faculty meeting will be held in the conference room.",
            "Today"
        ),

        TeacherNotice(
            "Internal Assessment",
            "Please submit internal assessment marks before the deadline.",
            "18 Sep 2026"
        ),

        TeacherNotice(
            "Semester Examination",
            "End semester examination schedule has been released.",
            "15 Sep 2026"
        ),

        TeacherNotice(
            "Department Workshop",
            "A technical workshop is scheduled for next week.",
            "12 Sep 2026"
        )
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFF3FBFD)
            )
    ) {

        // ==================================================
        // HEADER
        // SAME STYLE AS HOME / DEPARTMENT
        // ==================================================

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        bottomStart = 40.dp,
                        bottomEnd = 40.dp
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
                    start = 36.dp,
                    end = 36.dp,
                    top = 22.dp,
                    bottom = 28.dp
                )
        ) {

            // ----------------------------------------------
            // TOP ROW
            // ----------------------------------------------

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(
                            RoundedCornerShape(20.dp)
                        )
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "📢",
                        fontSize = 34.sp
                    )
                }


                Spacer(
                    modifier = Modifier.width(18.dp)
                )


                Column {

                    Text(
                        text = "UniGuide",
                        color = Color.White,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Teacher Portal",
                        color = Color.White.copy(
                            alpha = 0.85f
                        ),
                        fontSize = 16.sp
                    )
                }
            }


            // ----------------------------------------------
            // SPACE
            // ----------------------------------------------

            Spacer(
                modifier = Modifier.height(25.dp)
            )


            // ----------------------------------------------
            // PAGE TITLE
            // ----------------------------------------------

            Text(
                text = "Notices",
                color = Color.White,
                fontSize = 29.sp,
                fontWeight = FontWeight.Bold
            )


            // ----------------------------------------------
            // SPACE
            // ----------------------------------------------

            Spacer(
                modifier = Modifier.height(7.dp)
            )


            // ----------------------------------------------
            // PAGE SUBTITLE
            // ----------------------------------------------

            Text(
                text = "Important updates and announcements.",
                color = Color.White.copy(
                    alpha = 0.9f
                ),
                fontSize = 15.sp
            )
        }


        // ==================================================
        // NOTICE CONTENT
        // ==================================================

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 20.dp,
                    vertical = 20.dp
                )
        ) {

            Text(
                text = "Recent Notices",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            )


            Spacer(
                modifier = Modifier.height(15.dp)
            )


            notices.forEach { notice ->

                NoticeCard(
                    notice = notice
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )
            }
        }
    }
}


// ==========================================================
// NOTICE CARD
// ==========================================================

@Composable
fun NoticeCard(
    notice: TeacherNotice
) {

    Card(
        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(18.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            // ----------------------------------------------
            // NOTICE HEADER
            // ----------------------------------------------

            Row(
                modifier = Modifier.fillMaxWidth(),

                verticalAlignment = Alignment.CenterVertically,

                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // ------------------------------------------
                    // NOTICE ICON
                    // ------------------------------------------

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                Color(0xFFEFF6FF),
                                RoundedCornerShape(14.dp)
                            ),

                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "📢",
                            fontSize = 18.sp
                        )
                    }


                    Spacer(
                        modifier = Modifier.size(12.dp)
                    )


                    // ------------------------------------------
                    // NOTICE TITLE
                    // ------------------------------------------

                    Text(
                        text = notice.title,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF111827)
                    )
                }


                // ------------------------------------------
                // DATE
                // ------------------------------------------

                Text(
                    text = notice.date,
                    fontSize = 11.sp,
                    color = Color(0xFF64748B)
                )
            }


            // ----------------------------------------------
            // SPACE
            // ----------------------------------------------

            Spacer(
                modifier = Modifier.height(12.dp)
            )


            // ----------------------------------------------
            // DESCRIPTION
            // ----------------------------------------------

            Text(
                text = notice.description,
                fontSize = 13.sp,
                color = Color(0xFF64748B),
                lineHeight = 19.sp
            )
        }
    }
}


// ==========================================================
// PREVIEW
// ==========================================================

@Preview(showBackground = true)
@Composable
fun TeacherNoticesScreenPreview() {

    TeacherNoticesScreen()
}