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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class TeacherNotice(
    val title: String,
    val description: String,
    val date: String
)

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
            .background(Color(0xFFF7F9FC))
    ) {

        // -----------------------------
        // HEADER
        // -----------------------------

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF1769E0),
                            Color(0xFF4F8EF7),
                            Color(0xFF74B9FF)
                        )
                    ),
                    shape = RoundedCornerShape(
                        bottomStart = 32.dp,
                        bottomEnd = 32.dp
                    )
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp,
                        vertical = 40.dp
                    )
            ) {

                Text(
                    text = "📢",
                    fontSize = 35.sp
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Notices",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "Important updates and announcements",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 14.sp
                )
            }
        }

        // -----------------------------
        // NOTICE CONTENT
        // -----------------------------

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

@Composable
fun NoticeCard(
    notice: TeacherNotice
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
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
                .padding(18.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .background(
                                Color(0xFFEFF6FF),
                                RoundedCornerShape(14.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "📢",
                            fontSize = 22.sp
                        )
                    }

                    Spacer(
                        modifier = Modifier.size(12.dp)
                    )

                    Text(
                        text = notice.title,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF111827)
                    )
                }

                Text(
                    text = notice.date,
                    fontSize = 11.sp,
                    color = Color(0xFF64748B)
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = notice.description,
                fontSize = 13.sp,
                color = Color(0xFF64748B),
                lineHeight = 19.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TeacherNoticesScreenPreview() {
    TeacherNoticesScreen()
}