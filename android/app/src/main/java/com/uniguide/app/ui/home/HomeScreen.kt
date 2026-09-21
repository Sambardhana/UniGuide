package com.uniguide.app.ui.home


import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val homeItems = listOf(
    "University",
    "Academics",
    "Hostel",
    "Facilities",
    "Activities",
    "Events",
    "Campus Map",
    "Emergency Contacts"
)

@Composable
fun HomeScreen() {

    var searchText by remember {
        mutableStateOf("")
    }

    val filteredItems = homeItems.filter {
        it.contains(searchText, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3FCFD))
    ) {

        // ---------------------------------------------------------
        // TOP HEADER
        // ---------------------------------------------------------

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(235.dp)
                .clip(
                    RoundedCornerShape(
                        bottomStart = 32.dp,
                        bottomEnd = 32.dp
                    )
                )
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF07516A),
                            Color(0xFF1498AA),
                            Color(0xFFBFE8EC)
                        )
                    )
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 28.dp
                    )
            ) {

                // -------------------------------------------------
                // APP LOGO + NAME
                // -------------------------------------------------

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(
                                RoundedCornerShape(16.dp)
                            )
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "🎓",
                            fontSize = 27.sp
                        )
                    }

                    Spacer(
                        modifier = Modifier.width(14.dp)
                    )

                    Column {

                        Text(
                            text = "UniGuide",
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Student Portal",
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 13.sp
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(25.dp)
                )

                // -------------------------------------------------
                // GREETING
                // -------------------------------------------------

                Text(
                    text = "Hello, Student 👋",
                    color = Color.White,
                    fontSize = 29.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = "Everything you need to explore your university.",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 14.sp
                )
            }
        }

        // ---------------------------------------------------------
        // SEARCH BAR
        // ---------------------------------------------------------

        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp
                )
                .padding(
                    top = 16.dp
                ),
            placeholder = {
                Text(
                    text = "Search UniGuide"
                )
            },
            leadingIcon = {
                Text(
                    text = "🔍",
                    fontSize = 20.sp
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(18.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color(0xFF1498AA),
                unfocusedIndicatorColor = Color(0xFFE1E7EF)
            )
        )

        // ---------------------------------------------------------
        // SECTION TITLE
        // ---------------------------------------------------------

        Text(
            text = "What would you like to explore?",
            modifier = Modifier.padding(
                start = 20.dp,
                top = 18.dp,
                bottom = 12.dp
            ),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF07516A)
        )

        // ---------------------------------------------------------
        // FEATURE GRID
        // ---------------------------------------------------------

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                bottom = 20.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(filteredItems) { item ->

                HomeFeatureCard(
                    title = item
                )
            }
        }
    }
}

// -------------------------------------------------------------
// HOME FEATURE CARD
// -------------------------------------------------------------

@Composable
private fun HomeFeatureCard(
    title: String
) {

    val icon = when (title) {
        "University" -> "🏛️"
        "Academics" -> "🎓"
        "Hostel" -> "🏠"
        "Facilities" -> "🏢"
        "Activities" -> "⚽"
        "Events" -> "📅"
        "Campus Map" -> "🗺️"
        "Emergency Contacts" -> "🚨"
        else -> "📌"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Icon
            Text(
                text = icon,
                fontSize = 32.sp
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            // Title
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF07516A)
            )
        }
    }
}