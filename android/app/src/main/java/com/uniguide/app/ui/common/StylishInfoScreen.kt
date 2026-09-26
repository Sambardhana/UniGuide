package com.uniguide.app.ui.common

import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class StylishItem(
    val icon: String,
    val title: String,
    val subtitle: String,
    val date: String = "",
    val details: String
)

@Composable
fun StylishInfoScreen(
    title: String,
    subtitle: String,
    headerIcon: String,
    items: List<StylishItem>
) {

    var selectedItem by remember {
        mutableStateOf<StylishItem?>(null)
    }

    var expandedTitle by remember {
        mutableStateOf<String?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F8FC))
    ) {

        // ---------------- HEADER ----------------

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF008FA3),
                            Color(0xFF36B8C4),
                            Color(0xFF75D6D9)
                        )
                    ),
                    shape = RoundedCornerShape(
                        bottomStart = 52.dp,
                        bottomEnd = 52.dp
                    )
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 30.dp,
                        end = 30.dp,
                        top = 38.dp,
                        bottom = 28.dp
                    ),
                verticalArrangement = Arrangement.Bottom
            ) {

                Text(
                    text = headerIcon,
                    fontSize = 42.sp
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = title,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = subtitle,
                    fontSize = 17.sp,
                    color = Color.White.copy(alpha = 0.95f),
                    lineHeight = 23.sp
                )
            }
        }

        // ---------------- CONTENT ----------------

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 28.dp,
                bottom = 30.dp
            ),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            item {

                Text(
                    text = "Recent Information",
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF172033),
                    modifier = Modifier.padding(
                        start = 10.dp,
                        bottom = 2.dp
                    )
                )
            }

            items(items) { item ->

                val expanded = expandedTitle == item.title

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize()
                        .shadow(
                            elevation = if (expanded) 8.dp else 3.dp,
                            shape = RoundedCornerShape(25.dp)
                        )
                        .clickable {

                            expandedTitle =
                                if (expanded) {
                                    null
                                } else {
                                    item.title
                                }

                            selectedItem = item
                        },
                    shape = RoundedCornerShape(25.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 1.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(22.dp)
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            // ICON BOX
                            Box(
                                modifier = Modifier
                                    .size(58.dp)
                                    .background(
                                        Color(0xFFF0F6FF),
                                        RoundedCornerShape(18.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {

                                Text(
                                    text = item.icon,
                                    fontSize = 28.sp
                                )
                            }

                            Spacer(
                                modifier = Modifier.size(16.dp)
                            )

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {

                                Text(
                                    text = item.title,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF172033)
                                )

                                if (item.date.isNotEmpty()) {

                                    Spacer(
                                        modifier = Modifier.height(5.dp)
                                    )

                                    Text(
                                        text = item.date,
                                        fontSize = 13.sp,
                                        color = Color(0xFF667085)
                                    )
                                }
                            }
                        }

                        Spacer(
                            modifier = Modifier.height(15.dp)
                        )

                        Text(
                            text = item.subtitle,
                            fontSize = 16.sp,
                            color = Color(0xFF667085),
                            lineHeight = 24.sp
                        )

                        // Expanded part

                        if (expanded) {

                            Spacer(
                                modifier = Modifier.height(15.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        Color(0xFFF3FAFB),
                                        RoundedCornerShape(16.dp)
                                    )
                                    .padding(15.dp)
                            ) {

                                Text(
                                    text = item.details,
                                    fontSize = 15.sp,
                                    color = Color(0xFF344054),
                                    lineHeight = 23.sp
                                )
                            }

                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )

                            Text(
                                text = "Tap again to close",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF008FA3)
                            )
                        }
                    }
                }
            }
        }
    }

    // ---------------- POPUP ----------------

    selectedItem?.let { item ->

        AlertDialog(

            onDismissRequest = {
                selectedItem = null
            },

            icon = {
                Text(
                    text = item.icon,
                    fontSize = 40.sp
                )
            },

            title = {
                Text(
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            },

            text = {

                Column {

                    if (item.date.isNotEmpty()) {

                        Text(
                            text = item.date,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF008FA3)
                        )

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )
                    }

                    Text(
                        text = item.details,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        color = Color(0xFF475467)
                    )
                }
            },

            confirmButton = {

                TextButton(
                    onClick = {
                        selectedItem = null
                    }
                ) {

                    Text(
                        text = "Close",
                        color = Color(0xFF008FA3),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        )
    }
}