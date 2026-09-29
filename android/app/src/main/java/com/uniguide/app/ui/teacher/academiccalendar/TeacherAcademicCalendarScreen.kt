package com.uniguide.app.ui.teacher.academiccalendar

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
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


// ==========================================================
// DATA CLASSES
// ==========================================================

private data class CalendarEntry(
    val activity: String,
    val date: String
)


private data class SemesterCalendar(
    val title: String,
    val subtitle: String,
    val entries: List<CalendarEntry>
)


private data class ProgrammeCalendar(
    val title: String,
    val school: String,
    val icon: String,
    val semesters: List<SemesterCalendar>
)


// ==========================================================
// B.TECH - 1ST YEAR
// ==========================================================

private val btechYear1Odd = SemesterCalendar(
    title = "Semester 1",
    subtitle = "Odd Semester • 1st Year",

    entries = listOf(

        CalendarEntry(
            activity = "Subject Depository to be sent to ERP",
            date = "3rd August 2026"
        ),

        CalendarEntry(
            activity = "Subject Registration and Orientation Program",
            date = "4th to 6th August 2026"
        ),

        CalendarEntry(
            activity = "Time Table Configuration in ERP",
            date = "8th August 2026"
        ),

        CalendarEntry(
            activity = "Commencement of Classes",
            date = "10th August 2026"
        ),

        CalendarEntry(
            activity = "Midterm Examination",
            date = "8th to 15th October 2026"
        ),

        CalendarEntry(
            activity = "Last Date of Instruction",
            date = "3rd December 2026"
        ),

        CalendarEntry(
            activity = "Practical / Project / Thesis Examination",
            date = "7th to 11th December 2026"
        ),

        CalendarEntry(
            activity = "End Semester Theory Examination",
            date = "14th to 28th December 2026"
        )
    )
)


private val btechYear1Even = SemesterCalendar(
    title = "Semester 2",
    subtitle = "Even Semester • 1st Year",

    entries = listOf(

        CalendarEntry(
            activity = "Subject Registration",
            date = "29th to 30th December 2026"
        ),

        CalendarEntry(
            activity = "Timetable Configuration and Session Plan Upload in ERP",
            date = "31st December 2026 to 2nd January 2027"
        ),

        CalendarEntry(
            activity = "Commencement of Classes",
            date = "4th January 2027"
        ),

        CalendarEntry(
            activity = "Midterm Examination",
            date = "8th to 15th March 2027"
        ),

        CalendarEntry(
            activity = "Last Date of Instruction",
            date = "24th April 2027"
        ),

        CalendarEntry(
            activity = "Practical / Project / Thesis Examination",
            date = "28th April to 4th May 2027"
        ),

        CalendarEntry(
            activity = "End Semester Theory Examination",
            date = "5th to 19th May 2027"
        )
    )
)


// ==========================================================
// B.TECH - 2ND / 3RD / 4TH YEAR
// ==========================================================

private val btechSeniorOdd = SemesterCalendar(
    title = "Semesters 3, 5 & 7",
    subtitle = "Odd Semester • 2nd / 3rd / 4th Year",

    entries = listOf(

        CalendarEntry(
            activity = "Subject Depository to be sent to ERP",
            date = "1st to 5th June 2026"
        ),

        CalendarEntry(
            activity = "Subject Registration",
            date = "10th to 25th June 2026"
        ),

        CalendarEntry(
            activity = "Timetable Configuration and Session Plan Upload in ERP",
            date = "1st to 4th July 2026"
        ),

        CalendarEntry(
            activity = "Commencement of Classes",
            date = "6th July 2026"
        ),

        CalendarEntry(
            activity = "Mid Semester Examination",
            date = "7th to 11th September 2026"
        ),

        CalendarEntry(
            activity = "Last Date of Instruction",
            date = "31st October 2026"
        ),

        CalendarEntry(
            activity = "Practical / Project / Thesis Examination",
            date = "4th to 10th November 2026"
        ),

        CalendarEntry(
            activity = "End Semester Theory Examination",
            date = "12th to 28th November 2026"
        )
    )
)


private val btechSeniorEven = SemesterCalendar(
    title = "Semesters 4, 6 & 8",
    subtitle = "Even Semester • 2nd / 3rd / 4th Year",

    entries = listOf(

        CalendarEntry(
            activity = "Subject Depository to be sent to ERP",
            date = "20th to 25th November 2026"
        ),

        CalendarEntry(
            activity = "Subject Registration",
            date = "30th November to 2nd December 2026"
        ),

        CalendarEntry(
            activity = "Timetable Configuration and Session Plan Upload in ERP",
            date = "3rd to 5th December 2026"
        ),

        CalendarEntry(
            activity = "Commencement of Classes",
            date = "7th December 2026"
        ),

        CalendarEntry(
            activity = "Mid Semester Examination",
            date = "27th January to 2nd February 2027"
        ),

        CalendarEntry(
            activity = "Last Date of Instruction",
            date = "3rd April 2027"
        ),

        CalendarEntry(
            activity = "Practical / Project / Thesis Examination",
            date = "7th to 13th April 2027"
        ),

        CalendarEntry(
            activity = "End Semester Theory Examination",
            date = "15th to 30th April 2027"
        )
    )
)


// ==========================================================
// MCA - 1ST YEAR
// ==========================================================

private val mcaYear1Odd = SemesterCalendar(
    title = "Semester 1",
    subtitle = "Odd Semester • 1st Year",

    entries = listOf(

        CalendarEntry(
            activity = "Subject Registration and Orientation Program",
            date = "4th to 6th August 2026"
        ),

        CalendarEntry(
            activity = "Time Table Configuration in ERP",
            date = "8th August 2026"
        ),

        CalendarEntry(
            activity = "Commencement of Classes",
            date = "24th August 2026"
        ),

        CalendarEntry(
            activity = "Midterm Examination",
            date = "12th to 15th October 2026"
        ),

        CalendarEntry(
            activity = "Last Date of Instruction",
            date = "11th December 2026"
        ),

        CalendarEntry(
            activity = "Practical / Project / Thesis Examination",
            date = "16th to 19th December 2026"
        ),

        CalendarEntry(
            activity = "End Semester Theory Examination",
            date = "21st to 28th December 2026"
        )
    )
)


private val mcaYear1Even = SemesterCalendar(
    title = "Semester 2",
    subtitle = "Even Semester • 1st Year",

    entries = listOf(

        CalendarEntry(
            activity = "Subject Registration",
            date = "29th to 30th December 2026"
        ),

        CalendarEntry(
            activity = "Timetable Configuration and Session Plan Upload in ERP",
            date = "31st December 2026 to 2nd January 2027"
        ),

        CalendarEntry(
            activity = "Commencement of Classes",
            date = "4th January 2027"
        ),

        CalendarEntry(
            activity = "Midterm Examination",
            date = "8th to 15th March 2027"
        ),

        CalendarEntry(
            activity = "Last Date of Instruction",
            date = "24th April 2027"
        ),

        CalendarEntry(
            activity = "Practical / Project / Thesis Examination",
            date = "28th April to 4th May 2027"
        ),

        CalendarEntry(
            activity = "End Semester Theory Examination",
            date = "5th to 19th May 2027"
        )
    )
)


// ==========================================================
// MCA - 2ND YEAR
// ==========================================================

private val mcaYear2Odd = SemesterCalendar(
    title = "Semester 3",
    subtitle = "Odd Semester • 2nd Year",

    entries = listOf(

        CalendarEntry(
            activity = "Subject Depository to be sent to ERP",
            date = "1st to 5th June 2026"
        ),

        CalendarEntry(
            activity = "Subject Registration",
            date = "10th to 25th June 2026"
        ),

        CalendarEntry(
            activity = "Timetable Configuration and Session Plan Upload in ERP",
            date = "1st to 4th July 2026"
        ),

        CalendarEntry(
            activity = "Commencement of Classes",
            date = "6th July 2026"
        ),

        CalendarEntry(
            activity = "Mid Semester Examination",
            date = "7th to 11th September 2026"
        ),

        CalendarEntry(
            activity = "Last Date of Instruction",
            date = "31st October 2026"
        ),

        CalendarEntry(
            activity = "Practical / Project / Thesis Examination",
            date = "4th to 10th November 2026"
        ),

        CalendarEntry(
            activity = "End Semester Theory Examination",
            date = "12th to 28th November 2026"
        )
    )
)


private val mcaYear2Even = SemesterCalendar(
    title = "Semester 4",
    subtitle = "Even Semester • 2nd Year",

    entries = listOf(

        CalendarEntry(
            activity = "Subject Depository to be sent to ERP",
            date = "20th to 25th November 2026"
        ),

        CalendarEntry(
            activity = "Subject Registration",
            date = "30th November to 2nd December 2026"
        ),

        CalendarEntry(
            activity = "Timetable Configuration and Session Plan Upload in ERP",
            date = "3rd to 5th December 2026"
        ),

        CalendarEntry(
            activity = "Commencement of Classes",
            date = "7th December 2026"
        ),

        CalendarEntry(
            activity = "Mid Semester Examination",
            date = "27th January to 2nd February 2027"
        ),

        CalendarEntry(
            activity = "Last Date of Instruction",
            date = "3rd April 2027"
        ),

        CalendarEntry(
            activity = "Practical / Project / Thesis Examination",
            date = "7th to 13th April 2027"
        ),

        CalendarEntry(
            activity = "End Semester Theory Examination",
            date = "15th to 30th April 2027"
        )
    )
)


// ==========================================================
// PROGRAMMES
// ==========================================================

private val programmeCalendars = listOf(

    ProgrammeCalendar(
        title = "B.Tech",
        school = "School of Engineering & Technology",
        icon = "🎓",
        semesters = listOf(
            btechYear1Odd,
            btechYear1Even,
            btechSeniorOdd,
            btechSeniorEven
        )
    ),

    ProgrammeCalendar(
        title = "MCA",
        school = "School of Computing & Data Science",
        icon = "💻",
        semesters = listOf(
            mcaYear1Odd,
            mcaYear1Even,
            mcaYear2Odd,
            mcaYear2Even
        )
    )
)


// ==========================================================
// MAIN SCREEN
// ==========================================================

@Composable
fun TeacherAcademicCalendarScreen() {

    var selectedProgramme by remember {
        mutableStateOf<String?>(null)
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
                    top = 36.dp,
                    bottom = 28.dp
                )
        ) {

            Text(
                text = "🗓️",
                fontSize = 30.sp
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Academic Calendar",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = "Academic Year 2026–27",
                color = Color.White.copy(
                    alpha = 0.9f
                ),
                fontSize = 14.sp
            )
        }


        // ==================================================
        // PROGRAMME LIST
        // ==================================================

        LazyColumn(

            modifier = Modifier.fillMaxSize(),

            verticalArrangement = Arrangement.spacedBy(
                12.dp
            ),

            contentPadding = PaddingValues(
                start = 22.dp,
                end = 22.dp,
                top = 18.dp,
                bottom = 25.dp
            )
        ) {

            items(
                programmeCalendars
            ) { programme ->

                ProgrammeCalendarCard(
                    programme = programme,
                    isSelected = selectedProgramme == programme.title,
                    onClick = {

                        selectedProgramme =
                            if (
                                selectedProgramme ==
                                programme.title
                            ) {
                                null
                            } else {
                                programme.title
                            }
                    }
                )


                if (
                    selectedProgramme ==
                    programme.title
                ) {

                    programme.semesters.forEach { semester ->

                        SemesterCard(
                            semester = semester
                        )
                    }
                }
            }
        }
    }
}


// ==========================================================
// PROGRAMME CARD
// ==========================================================

@Composable
private fun ProgrammeCalendarCard(
    programme: ProgrammeCalendar,
    isSelected: Boolean,
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
                .padding(18.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(55.dp)
                    .clip(
                        RoundedCornerShape(16.dp)
                    )
                    .background(
                        Color(0xFFE8F7F9)
                    ),

                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = programme.icon,
                    fontSize = 25.sp
                )
            }


            Spacer(
                modifier = Modifier.width(14.dp)
            )


            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = programme.title,
                    color = Color(0xFF07516A),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = programme.school,
                    color = Color(0xFF64777B),
                    fontSize = 13.sp
                )
            }


            Text(
                text = if (isSelected) {
                    "⌃"
                } else {
                    "›"
                },

                color = Color(0xFF08758A),

                fontSize = 28.sp,

                fontWeight = FontWeight.Bold
            )
        }
    }
}


// ==========================================================
// SEMESTER CARD
// ==========================================================

@Composable
private fun SemesterCard(
    semester: SemesterCalendar
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 8.dp,
                end = 8.dp
            ),

        shape = RoundedCornerShape(18.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            // --------------------------------------------
            // SEMESTER HEADER
            // --------------------------------------------

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFFE8F7F9)
                    )
                    .padding(16.dp)
            ) {

                Text(
                    text = semester.title,
                    color = Color(0xFF07516A),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(3.dp)
                )

                Text(
                    text = semester.subtitle,
                    color = Color(0xFF64777B),
                    fontSize = 12.sp
                )
            }


            // --------------------------------------------
            // CALENDAR ENTRIES
            // --------------------------------------------

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),

                verticalArrangement = Arrangement.spacedBy(
                    13.dp
                )
            ) {

                semester.entries.forEach { entry ->

                    CalendarEntryRow(
                        entry = entry
                    )
                }
            }
        }
    }
}


// ==========================================================
// CALENDAR ENTRY ROW
// ==========================================================

@Composable
private fun CalendarEntryRow(
    entry: CalendarEntry
) {

    Row(
        modifier = Modifier.fillMaxWidth(),

        verticalAlignment = Alignment.Top
    ) {

        Text(
            text = "•",
            color = Color(0xFF08758A),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.width(9.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = entry.activity,
                color = Color(0xFF334B52),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 19.sp
            )

            Spacer(
                modifier = Modifier.height(3.dp)
            )

            Text(
                text = entry.date,
                color = Color(0xFF08758A),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}