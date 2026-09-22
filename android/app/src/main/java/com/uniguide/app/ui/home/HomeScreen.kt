package com.uniguide.app.ui.home

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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private data class HomeItem(
    val title: String,
    val subtitle: String,
    val icon: String
)

private val homeItems = listOf(
    HomeItem(
        "University",
        "University information",
        "🏫"
    ),
    HomeItem(
        "Academics",
        "Departments & courses",
        "📚"
    ),
    HomeItem(
        "Hostel",
        "Hostel information",
        "🛏️"
    ),
    HomeItem(
        "Facilities",
        "Campus facilities",
        "🏢"
    ),
    HomeItem(
        "Activities",
        "Student activities",
        "🎭"
    ),
    HomeItem(
        "Events",
        "Upcoming events",
        "📅"
    ),
    HomeItem(
        "Campus Map",
        "Find places on campus",
        "🗺️"
    ),
    HomeItem(
        "Emergency Contacts",
        "Important contacts",
        "🚨"
    )
)

@Composable
fun HomeScreen(
    onItemClick: (String) -> Unit
) {

    var searchText by remember {
        mutableStateOf("")
    }

    val filteredItems = homeItems.filter {
        it.title.contains(searchText, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F9FC))
    ) {

        // -------------------------------
        // TOP HEADER
        // -------------------------------

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
                            Color(0xFF1769E0),
                            Color(0xFF4F8EF7),
                            Color(0xFF74B9FF)
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

                // Logo / App name
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(RoundedCornerShape(16.dp))
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

        // -------------------------------
        // SEARCH
        // -------------------------------

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
                .padding(top = 16.dp),
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
                focusedIndicatorColor = Color(0xFF3B82F6),
                unfocusedIndicatorColor = Color(0xFFE1E7EF)
            )
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // -------------------------------
        // SECTION TITLE
        // -------------------------------

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Explore UniGuide",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "${filteredItems.size} options",
                fontSize = 12.sp,
                color = Color(0xFF64748B)
            )
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        // -------------------------------
        // FEATURE CARDS
        // -------------------------------

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(
                start = 18.dp,
                end = 18.dp,
                bottom = 25.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            items(filteredItems) { item ->

                StylishHomeCard(
                    item = item,
                    onClick = {
                        onItemClick(item.title)
                    }
                )
            }
        }
    }
}

@Composable
private fun StylishHomeCard(
    item: HomeItem,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(165.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(24.dp)
            )
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(17.dp)
        ) {

            // Icon
            Box(
                modifier = Modifier
                    .size(55.dp)
                    .clip(RoundedCornerShape(17.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFEAF2FF),
                                Color(0xFFF3F7FF)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = item.icon,
                    fontSize = 28.sp
                )
            }

            Spacer(
                modifier = Modifier.height(13.dp)
            )

            Text(
                text = item.title,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = item.subtitle,
                fontSize = 12.sp,
                color = Color(0xFF64748B)
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "Explore  →",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2563EB)
            )
        }
    }
}