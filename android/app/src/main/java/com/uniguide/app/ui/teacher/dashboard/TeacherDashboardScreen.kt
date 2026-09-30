package com.uniguide.app.ui.teacher.dashboard

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uniguide.app.ui.common.FloatingAiButton


private data class TeacherItem(
    val title: String,
    val icon: String
)


private val teacherItems = listOf(

    TeacherItem(
        title = "University information",
        icon = "🏛️"
    ),

    TeacherItem(
        title = "Departments",
        icon = "🏢"
    ),

    TeacherItem(
        title = "Academic System ",
        icon = "📚"
    ),
    TeacherItem(
        title = "Academic Calendar",
        icon = "🗓️"
    ),
    TeacherItem(
        title = "Faculty Guidelines",
        icon = "📋"
    ),

    TeacherItem(
        title = "Notices",
        icon = "📢"
    ),

    TeacherItem(
        title = "Emergency & Support ",
        icon = "🆘"

    )
)


@Composable
fun TeacherDashboardScreen(
    onItemClick: (String) -> Unit,
    onAiClick: () -> Unit
) {

    var searchText by remember {
        mutableStateOf("")
    }


    val filteredItems = teacherItems.filter { item ->

        item.title.contains(
            searchText,
            ignoreCase = true
        )
    }


    // =================================================
    // MAIN SCREEN
    // =================================================

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFF3FBFD)
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            // =================================================
            // HEADER
            // =================================================

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

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(

                        modifier = Modifier
                            .size(72.dp)
                            .clip(
                                RoundedCornerShape(20.dp)
                            )
                            .background(
                                Color.White
                            ),

                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "👨‍🏫",
                            fontSize = 28.sp
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


                Spacer(
                    modifier = Modifier.height(25.dp)
                )


                Text(
                    text = "Hello, Teacher 👋",
                    color = Color.White,
                    fontSize = 29.sp,
                    fontWeight = FontWeight.Bold
                )


                Spacer(
                    modifier = Modifier.height(7.dp)
                )


                Text(
                    text = "Everything you need to manage your university work.",
                    color = Color.White.copy(
                        alpha = 0.9f
                    ),
                    fontSize = 15.sp
                )
            }


            Spacer(
                modifier = Modifier.height(18.dp)
            )


            // =================================================
            // SEARCH
            // =================================================

            OutlinedTextField(

                value = searchText,

                onValueChange = {
                    searchText = it
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 31.dp
                    )
                    .height(68.dp),

                placeholder = {

                    Text(
                        text = "Search Teacher Portal",
                        color = Color(0xFF65777B),
                        fontSize = 17.sp
                    )
                },

                leadingIcon = {

                    Text(
                        text = "🔍",
                        fontSize = 23.sp
                    )
                },

                singleLine = true,

                shape = RoundedCornerShape(22.dp),

                colors = TextFieldDefaults.colors(

                    focusedContainerColor = Color.White,

                    unfocusedContainerColor = Color.White,

                    disabledContainerColor = Color.White,

                    focusedIndicatorColor = Color(0xFFD9E3E5),

                    unfocusedIndicatorColor = Color(0xFFD9E3E5),

                    cursorColor = Color(0xFF08758A)
                )
            )


            Spacer(
                modifier = Modifier.height(17.dp)
            )


            // =================================================
            // SECTION TITLE
            // =================================================

            Text(

                text = "What would you like to manage?",

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 31.dp
                    ),

                color = Color(0xFF07516A),

                fontSize = 19.sp,

                fontWeight = FontWeight.Bold
            )


            Spacer(
                modifier = Modifier.height(12.dp)
            )


            // =================================================
            // TEACHER CARDS
            // =================================================

            LazyVerticalGrid(

                columns = GridCells.Fixed(2),

                modifier = Modifier.fillMaxSize(),

                contentPadding = PaddingValues(

                    start = 30.dp,

                    end = 30.dp,

                    // Extra space for AI button
                    bottom = 110.dp
                ),

                horizontalArrangement = Arrangement.spacedBy(
                    16.dp
                ),

                verticalArrangement = Arrangement.spacedBy(
                    14.dp
                )
            ) {

                items(filteredItems) { item ->

                    TeacherFeatureCard(

                        item = item,

                        onClick = {
                            onItemClick(item.title)
                        }
                    )
                }
            }
        }


        // =================================================
        // FLOATING AI BUTTON
        // =================================================

        FloatingAiButton(

            onClick = {
                onAiClick()
            },

            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 20.dp,
                    bottom = 25.dp
                )
        )
    }
}


// =================================================
// TEACHER FEATURE CARD
// =================================================

@Composable
private fun TeacherFeatureCard(

    item: TeacherItem,

    onClick: () -> Unit
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = item.icon,
                fontSize = 23.sp,
                textAlign = TextAlign.Center
            )


            Spacer(
                modifier = Modifier.height(9.dp)
            )


            Text(
                text = item.title,
                color = Color(0xFF07516A),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}