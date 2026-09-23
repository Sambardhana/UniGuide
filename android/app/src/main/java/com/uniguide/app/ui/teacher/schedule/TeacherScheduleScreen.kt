package com.uniguide.app.ui.teacher.schedule

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

data class ScheduleItem(
    val time: String,
    val subject: String,
    val className: String,
    val room: String
)

@Composable
fun TeacherScheduleScreen() {

    val schedule = listOf(
        ScheduleItem(
            "09:00 AM",
            "Data Structures",
            "CSE 3rd Semester",
            "Room 201"
        ),
        ScheduleItem(
            "11:00 AM",
            "Database Management",
            "CSE 5th Semester",
            "Room 305"
        ),
        ScheduleItem(
            "01:00 PM",
            "Computer Networks",
            "CSE 5th Semester",
            "Room 204"
        ),
        ScheduleItem(
            "03:00 PM",
            "Software Engineering",
            "CSE 7th Semester",
            "Room 301"
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
                    text = "📅",
                    fontSize = 35.sp
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "My Schedule",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "Today's classes and teaching schedule",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 14.sp
                )
            }
        }

        // -----------------------------
        // SCHEDULE CONTENT
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
                text = "Today's Schedule",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            )

            Spacer(
                modifier = Modifier.height(15.dp)
            )

            schedule.forEach { item ->

                ScheduleCard(
                    item = item
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )
            }
        }
    }
}

@Composable
fun ScheduleCard(
    item: ScheduleItem
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // TIME

            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(
                        Color(0xFFEFF6FF),
                        RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = item.time,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2563EB)
                )
            }

            Spacer(
                modifier = Modifier.size(16.dp)
            )

            // CLASS INFORMATION

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = item.subject,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF111827)
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = item.className,
                    fontSize = 13.sp,
                    color = Color(0xFF64748B)
                )

                Spacer(
                    modifier = Modifier.height(3.dp)
                )

                Text(
                    text = "📍 ${item.room}",
                    fontSize = 12.sp,
                    color = Color(0xFF94A3B8)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TeacherScheduleScreenPreview() {
    TeacherScheduleScreen()
}