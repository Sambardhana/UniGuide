package com.uniguide.app.ui.teacher.department

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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


data class SchoolItem(
    val id: String,
    val name: String,
    val icon: String
)


private val cutmSchools = listOf(

    SchoolItem(
        id = "engineering",
        name = "School of Engineering & Technology",
        icon = "⚙️"
    ),

    SchoolItem(
        id = "fisheries",
        name = "School of Fisheries",
        icon = "🐟"
    ),

    SchoolItem(
        id = "media",
        name = "School of Media & Communication",
        icon = "🎙️"
    ),

    SchoolItem(
        id = "agriculture",
        name = "M.S. Swaminathan School of Agriculture",
        icon = "🌾"
    ),

    SchoolItem(
        id = "management",
        name = "School of Management",
        icon = "💼"
    ),

    SchoolItem(
        id = "forensic",
        name = "School of Forensic Sciences",
        icon = "🔬"
    ),

    SchoolItem(
        id = "law",
        name = "School of Law",
        icon = "⚖️"
    ),

    SchoolItem(
        id = "applied_sciences",
        name = "School of Applied Sciences",
        icon = "🧪"
    ),

    SchoolItem(
        id = "pharmacy",
        name = "School of Pharmacy & Life Sciences",
        icon = "💊"
    ),

    SchoolItem(
        id = "agri_bio",
        name = "School of Agriculture & Bio-Engineering",
        icon = "🌱"
    ),

    SchoolItem(
        id = "allied_health",
        name = "School of Allied & Healthcare Sciences",
        icon = "🏥"
    ),

    SchoolItem(
        id = "biotechnology",
        name = "School of Biotechnology",
        icon = "🧬"
    ),

    SchoolItem(
        id = "bachelor",
        name = "School of Bachelor Studies",
        icon = "🎓"
    ),

    SchoolItem(
        id = "veterinary",
        name = "School of Veterinary & Animal Sciences",
        icon = "🐾"
    ),

    SchoolItem(
        id = "nursing",
        name = "School of Nursing",
        icon = "🩺"
    ),

    SchoolItem(
        id = "design",
        name = "School of Design Studies",
        icon = "🎨"
    ),

    SchoolItem(
        id = "vocational",
        name = "School of Vocational Education & Training",
        icon = "🛠️"
    )
)


@Composable
fun DepartmentScreen(
    onSchoolClick: (String) -> Unit
) {

    var searchText by remember {
        mutableStateOf("")
    }


    val filteredSchools = cutmSchools.filter { school ->

        school.name.contains(
            searchText,
            ignoreCase = true
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFF3FBFD)
            )
    ) {

        // ==================================================
        // HEADER
        // ==================================================

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        bottomStart = 32.dp,
                        bottomEnd = 32.dp
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
                    start = 26.dp,
                    end = 26.dp,
                    top = 38.dp,
                    bottom = 28.dp
                )
        ) {

            Text(
                text = "🏢",
                fontSize = 29.sp
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Departments",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = "Explore CUTM academic schools",
                color = Color.White.copy(
                    alpha = 0.9f
                ),
                fontSize = 14.sp
            )
        }


        Spacer(
            modifier = Modifier.height(16.dp)
        )


        // ==================================================
        // SEARCH
        // ==================================================

        OutlinedTextField(

            value = searchText,

            onValueChange = {
                searchText = it
            },

            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 22.dp
                )
                .height(62.dp),

            placeholder = {

                Text(
                    text = "Search schools",
                    color = Color(0xFF65777B),
                    fontSize = 16.sp
                )
            },

            leadingIcon = {

                Text(
                    text = "🔍",
                    fontSize = 21.sp
                )
            },

            singleLine = true,

            shape = RoundedCornerShape(20.dp),

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
            modifier = Modifier.height(15.dp)
        )


        Text(
            text = "CUTM Academic Schools",

            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 22.dp
                ),

            color = Color(0xFF07516A),

            fontSize = 19.sp,

            fontWeight = FontWeight.Bold
        )


        Spacer(
            modifier = Modifier.height(10.dp)
        )


        // ==================================================
        // SCHOOL LIST
        // ==================================================

        LazyColumn(

            modifier = Modifier.fillMaxSize(),

            verticalArrangement = Arrangement.spacedBy(
                12.dp
            ),

            contentPadding = androidx.compose.foundation.layout.PaddingValues(
                start = 22.dp,
                end = 22.dp,
                bottom = 22.dp
            )
        ) {

            items(filteredSchools) { school ->

                SchoolCard(
                    school = school,
                    onClick = {
                        onSchoolClick(school.id)
                    }
                )
            }
        }
    }
}


// ==========================================================
// SCHOOL CARD
// ==========================================================

@Composable
private fun SchoolCard(
    school: SchoolItem,
    onClick: () -> Unit
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(20.dp),

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
                .padding(16.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(

                modifier = Modifier
                    .size(52.dp)
                    .clip(
                        RoundedCornerShape(15.dp)
                    )
                    .background(
                        Color(0xFFE8F7F9)
                    ),

                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = school.icon,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }


            Spacer(
                modifier = Modifier.width(14.dp)
            )


            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = school.name,
                    color = Color(0xFF07516A),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "Tap to view school details",
                    color = Color(0xFF64777B),
                    fontSize = 13.sp
                )
            }


            Text(
                text = "›",
                color = Color(0xFF08758A),
                fontSize = 30.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}