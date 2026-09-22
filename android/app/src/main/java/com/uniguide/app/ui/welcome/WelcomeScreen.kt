package com.uniguide.app.ui.welcome


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uniguide.app.R

@Composable
fun WelcomeScreen(
    onStudentClick: () -> Unit,
    onTeacherClick: () -> Unit = {},
    onGetStartedClick: () -> Unit = {}
) {

    // UNIGUIDE COLORS

    val darkTeal = Color(0xFF08758A)
    val mainTeal = Color(0xFF1498AA)
    val lightTeal = Color(0xFFBFE8EC)

    val background = Color(0xFFF3FCFD)
    val darkText = Color(0xFF07516A)
    val secondaryText = Color(0xFF5F7E86)

    // MAIN SCREEN

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFE7F8FA),
                        background,
                        Color.White
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 20.dp,
                    vertical = 25.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            // LANGUAGE SELECTOR

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .padding(
                            horizontal = 15.dp,
                            vertical = 9.dp
                        )
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "🌐",
                            fontSize = 16.sp
                        )

                        Spacer(
                            modifier = Modifier.size(6.dp)
                        )

                        Text(
                            text = "English",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = darkText
                        )

                        Spacer(
                            modifier = Modifier.size(5.dp)
                        )

                        Text(
                            text = "⌄",
                            fontSize = 17.sp,
                            color = darkTeal
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // UNIGUIDE LOGO

            Image(
                painter = painterResource(
                    id = R.drawable.uniguide_logo
                ),
                contentDescription = "UniGuide Logo",
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            // WELCOME TEXT


            Text(
                text = "Welcome to",
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium,
                color = mainTeal,
                textAlign = TextAlign.Center
            )

            Text(
                text = "UniGuide",
                fontSize = 31.sp,
                fontWeight = FontWeight.ExtraBold,
                color = darkTeal,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = "Your university companion for campus information, academics, hostel, activities and more.",
                fontSize = 14.sp,
                color = secondaryText,
                textAlign = TextAlign.Center,
                lineHeight = 21.sp
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // CAMPUS IMAGE

            Image(
                painter = painterResource(
                    id = R.drawable.campus_illustration
                ),
                contentDescription = "University Campus",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(155.dp)
                    .clip(
                        RoundedCornerShape(22.dp)
                    ),
                contentScale = ContentScale.Crop
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // -------------------------------------------------
            // STUDENT + TEACHER CARDS
            // -------------------------------------------------

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                // STUDENT CARD

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(105.dp)
                        .clickable {
                            onStudentClick()
                        },
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE9F9FB)
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 0.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "🎓",
                            fontSize = 34.sp
                        )

                        Spacer(
                            modifier = Modifier.height(5.dp)
                        )

                        Text(
                            text = "Student",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = darkText
                        )
                    }
                }

                // TEACHER CARD

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(105.dp)
                        .clickable {
                            onTeacherClick()
                        },
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE9F9FB)
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 0.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "👨‍🏫",
                            fontSize = 34.sp
                        )

                        Spacer(
                            modifier = Modifier.height(5.dp)
                        )

                        Text(
                            text = "Teacher",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = darkText
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // GET STARTED BUTTON

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(
                        RoundedCornerShape(20.dp)
                    )
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                darkTeal,
                                mainTeal
                            )
                        )
                    )
                    .clickable {
                        onGetStartedClick()
                    },
                contentAlignment = Alignment.Center
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Get Started",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(
                        modifier = Modifier.size(10.dp)
                    )

                    Text(
                        text = "→",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            // BOTTOM TEXT

            Text(
                text = "Explore • Connect • Build Your Future",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = mainTeal,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = "Your university information, all in one place.",
                fontSize = 11.sp,
                color = secondaryText,
                textAlign = TextAlign.Center
            )
        }
    }
}