package com.uniguide.app.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.uniguide.app.ui.theme.UniGuideBackground
import com.uniguide.app.ui.theme.UniGuideDarkText
import com.uniguide.app.ui.theme.UniGuideSecondaryText
import com.uniguide.app.ui.theme.UniGuideTeal
import com.uniguide.app.ui.theme.UniGuideWhite

@Composable
fun WelcomeScreen(
    onStudentClick: () -> Unit,
    onTeacherClick: () -> Unit = {},
    onGetStartedClick: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        UniGuideBackground,
                        Color.White
                    )
                )
            )
            .padding(
                horizontal = 20.dp,
                vertical = 18.dp
            )
    ) {

        // Top language selector
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "English",
                color = UniGuideDarkText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            IconButton(
                onClick = {
                    // Language selection will be added later.
                }
            ) {

                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Select language",
                    tint = UniGuideTeal
                )
            }
        }

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        // UniGuide Logo
        Image(
            painter = painterResource(
                id = R.drawable.uniguide_logo
            ),
            contentDescription = "UniGuide Logo",
            modifier = Modifier
                .size(125.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Fit
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        // Welcome text
        Text(
            text = "Welcome to",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = UniGuideDarkText,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = "UniGuide",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = UniGuideTeal,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // Campus illustration
        Image(
            painter = painterResource(
                id = R.drawable.campus_illustration
            ),
            contentDescription = "University Campus",
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp)
                .clip(
                    RoundedCornerShape(22.dp)
                ),
            contentScale = ContentScale.Crop
        )

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        Text(
            text = "Choose your role",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = UniGuideDarkText,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        // Student and Teacher buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            RoleCard(
                title = "Student",
                icon = Icons.Default.School,
                modifier = Modifier.weight(1f),
                onClick = onStudentClick
            )

            RoleCard(
                title = "Teacher",
                icon = Icons.Default.Person,
                modifier = Modifier.weight(1f),
                onClick = onTeacherClick
            )
        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        // Get Started
        Button(
            onClick = onGetStartedClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = UniGuideTeal,
                contentColor = UniGuideWhite
            )
        ) {

            Text(
                text = "Get Started →",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "Your guide to university life",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = UniGuideSecondaryText,
            fontSize = 13.sp
        )
    }
}

@Composable
private fun RoleCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier,
    onClick: () -> Unit
) {

    Card(
        modifier = modifier
            .height(90.dp),
        onClick = onClick,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = UniGuideWhite
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = UniGuideTeal,
                modifier = Modifier.size(30.dp)
            )

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Text(
                text = title,
                color = UniGuideDarkText,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}