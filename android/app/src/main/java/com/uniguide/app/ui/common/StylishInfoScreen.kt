package com.uniguide.app.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.window.Dialog

// ============================================================
// DATA MODELS
// ============================================================

data class InfoSection(
    val icon: String,
    val title: String,
    val description: String,
    val skills: List<String>
)

data class StylishItem(
    val icon: String,
    val title: String,
    val subtitle: String,
    val date: String = "",
    val details: String,
    val sections: List<InfoSection> = emptyList()
)

// ============================================================
// MAIN SCREEN
// ============================================================

@Composable
fun StylishInfoScreen(
    title: String,
    subtitle: String,
    headerIcon: String,
    items: List<StylishItem>,
    onItemClick: ((StylishItem) -> Boolean)? = null
) {

    var selectedItem by remember {
        mutableStateOf<StylishItem?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F8FC))
    ) {

        // ====================================================
        // HEADER
        // ====================================================

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(275.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF5B2EFF),
                            Color(0xFF7B4DFF),
                            Color(0xFF9B7BFF)
                        )
                    ),
                    shape = RoundedCornerShape(
                        bottomStart = 45.dp,
                        bottomEnd = 45.dp
                    )
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 28.dp,
                        end = 28.dp,
                        top = 35.dp,
                        bottom = 30.dp
                    ),
                verticalArrangement = Arrangement.Bottom
            ) {

                // HEADER ICON

                Box(
                    modifier = Modifier
                        .size(68.dp)
                        .background(
                            Color.White.copy(alpha = 0.18f),
                            RoundedCornerShape(22.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = headerIcon,
                        fontSize = 38.sp
                    )
                }

                Spacer(
                    modifier = Modifier.height(15.dp)
                )

                Text(
                    text = title,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = subtitle,
                    fontSize = 16.sp,
                    color = Color.White.copy(alpha = 0.92f),
                    lineHeight = 23.sp
                )
            }
        }

        // ====================================================
        // CONTENT
        // ====================================================

        LazyColumn(
            modifier = Modifier.fillMaxSize(),

            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 25.dp,
                bottom = 35.dp
            ),

            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 5.dp,
                            bottom = 3.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Explore Academics",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF172033)
                    )
                }
            }

            items(items) { item ->

                AcademicCard(
                    item = item,
                    onClick = {

                        val handled =
                            onItemClick?.invoke(item) ?: false

                        if (!handled) {
                            selectedItem = item
                        }
                    }
                )
            }
        }
    }

    // =========================================================
    // DETAIL DIALOG
    // =========================================================

    selectedItem?.let { item ->

        StylishDetailDialog(
            item = item,
            onDismiss = {
                selectedItem = null
            }
        )
    }
}

// ============================================================
// ACADEMIC CARD
// ============================================================

@Composable
private fun AcademicCard(
    item: StylishItem,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            // =================================================
            // ICON
            // =================================================

            Box(
                modifier = Modifier
                    .size(62.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFFF0EBFF),
                                Color(0xFFE7DEFF)
                            )
                        ),
                        shape = RoundedCornerShape(20.dp)
                    ),

                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = item.icon,
                    fontSize = 30.sp
                )
            }

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            // =================================================
            // TEXT
            // =================================================

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = item.title,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF172033)
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = item.subtitle,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = Color(0xFF667085)
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Tap to explore  →",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF6842E8)
                )
            }
        }
    }
}

// ============================================================
// DETAIL DIALOG
// ============================================================

@Composable
private fun StylishDetailDialog(
    item: StylishItem,
    onDismiss: () -> Unit
) {

    Dialog(
        onDismissRequest = onDismiss
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth(0.96f)
                .widthIn(max = 450.dp)
                .heightIn(max = 680.dp),

            shape = RoundedCornerShape(30.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                // =================================================
                // DIALOG HEADER
                // =================================================

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF5B2EFF),
                                    Color(0xFF8A68FF)
                                )
                            ),
                            shape = RoundedCornerShape(
                                topStart = 30.dp,
                                topEnd = 30.dp
                            )
                        )
                        .padding(
                            start = 22.dp,
                            end = 18.dp,
                            top = 22.dp,
                            bottom = 22.dp
                        )
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        // ICON

                        Box(
                            modifier = Modifier
                                .size(58.dp)
                                .background(
                                    Color.White.copy(alpha = 0.18f),
                                    RoundedCornerShape(18.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = item.icon,
                                fontSize = 30.sp
                            )
                        }

                        Spacer(
                            modifier = Modifier.width(14.dp)
                        )

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text = item.title,
                                fontSize = 23.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )

                            Spacer(
                                modifier = Modifier.height(3.dp)
                            )

                            Text(
                                text = "Explore opportunities",
                                fontSize = 13.sp,
                                color = Color.White.copy(alpha = 0.88f)
                            )
                        }

                        // CLOSE BUTTON

                        Text(
                            text = "✕",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .clickable {
                                    onDismiss()
                                }
                                .padding(8.dp)
                        )
                    }
                }

                // =================================================
                // SCROLLABLE CONTENT
                // =================================================

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = false)
                        .heightIn(max = 500.dp),

                    contentPadding = PaddingValues(
                        start = 20.dp,
                        end = 20.dp,
                        top = 20.dp,
                        bottom = 15.dp
                    ),

                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {

                    // ------------------------------------------------
                    // INTRODUCTION
                    // ------------------------------------------------

                    item {

                        Text(
                            text = item.details,
                            fontSize = 15.sp,
                            lineHeight = 23.sp,
                            color = Color(0xFF475467)
                        )
                    }

                    // ------------------------------------------------
                    // SKILL CATEGORIES
                    // ------------------------------------------------

                    if (item.sections.isNotEmpty()) {

                        items(item.sections) { section ->

                            SkillCategoryCard(
                                section = section
                            )
                        }
                    }
                }

                // =================================================
                // BOTTOM BUTTON
                // =================================================

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            bottom = 18.dp
                        )
                ) {

                    TextButton(
                        onClick = onDismiss,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text(
                            text = "Close",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF6842E8)
                        )
                    }
                }
            }
        }
    }
}

// ============================================================
// SKILL CATEGORY CARD
// ============================================================

@Composable
private fun SkillCategoryCard(
    section: InfoSection
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color(0xFFF8F7FF),
                RoundedCornerShape(20.dp)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFE8E2FF),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp)
    ) {

        // =====================================================
        // CATEGORY HEADER
        // =====================================================

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(46.dp)
                    .background(
                        Color(0xFFECE6FF),
                        RoundedCornerShape(15.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = section.icon,
                    fontSize = 24.sp
                )
            }

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Text(
                text = section.title,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF24213A)
            )
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        // =====================================================
        // DESCRIPTION
        // =====================================================

        Text(
            text = section.description,
            fontSize = 13.sp,
            lineHeight = 19.sp,
            color = Color(0xFF667085)
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        // =====================================================
        // SKILL CHIPS
        // =====================================================

        Column(
            verticalArrangement = Arrangement.spacedBy(7.dp)
        ) {

            section.skills.chunked(2).forEach { rowSkills ->

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(7.dp)
                ) {

                    rowSkills.forEach { skill ->

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(
                                    Color.White,
                                    RoundedCornerShape(12.dp)
                                )
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFE5E7EB),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .padding(
                                    horizontal = 9.dp,
                                    vertical = 8.dp
                                )
                        ) {

                            Text(
                                text = skill,
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                color = Color(0xFF344054),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    // Keep the last item from becoming too wide
                    if (rowSkills.size == 1) {

                        Spacer(
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}