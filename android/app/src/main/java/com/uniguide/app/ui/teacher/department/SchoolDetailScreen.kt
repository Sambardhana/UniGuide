package com.uniguide.app.ui.teacher.department

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


private data class SchoolDetails(
    val name: String,
    val icon: String,
    val description: String,
    val programs: List<String>,
    val head: String
)


private fun getSchoolDetails(
    id: String
): SchoolDetails {

    return when (id) {

        // ==================================================
        // ENGINEERING
        // ==================================================

        "engineering" -> SchoolDetails(
            name = "School of Engineering & Technology",
            icon = "⚙️",
            description = "The School of Engineering and Technology focuses on industry-integrated, project-based and multidisciplinary engineering education.",
            head = "Prof.(Dr.) Prafulla Kumar Panda — Dean, SoET & SoCDS",
            programs = listOf(
                "B.Tech Computer Science and Engineering",
                "B.Tech Computer Science and Engineering (AIML)",
                "B.Tech Mining Engineering",
                "B.Tech Civil Engineering",
                "B.Tech Mechanical Engineering",
                "B.Tech Electronics and Communication Engineering",
                "B.Tech Electrical and Electronics Engineering",
                "Bachelor of Computer Application",
                "M.Tech Design and Manufacturing",
                "M.Tech Transportation Engineering",
                "M.Tech Structural Engineering",
                "M.Tech Data Science",
                "M.Tech Power System and Control Engineering",
                "Master of Computer Applications"
            )
        )


        // ==================================================
        // FISHERIES
        // ==================================================

        "fisheries" -> SchoolDetails(
            name = "School of Fisheries",
            icon = "🐟",
            description = "The School of Fisheries was established in 2017 and focuses on fisheries, aquaculture, practical training and sustainable aquatic-resource development.",
            head = "Dr. Sambid Swain — Associate Dean, SoF",
            programs = listOf(
                "Bachelor of Fisheries Science (Hons.)",
                "Master in Fisheries Science (Aquaculture)"
            )
        )


        // ==================================================
        // MEDIA
        // ==================================================

        "media" -> SchoolDetails(
            name = "School of Media & Communication",
            icon = "🎙️",
            description = "The School of Media & Communication provides academic and skill-based education in media, communication, animation and related creative fields.",
            head = "Prof. Saban Kumar Maharana — Assistant Professor",
            programs = listOf(
                "Bachelor of Media and Communication",
                "Bachelor of Science Animation and Multimedia",
                "Master of Media and Communication"
            )
        )


        // ==================================================
        // AGRICULTURE
        // ==================================================

        "agriculture" -> SchoolDetails(
            name = "M.S. Swaminathan School of Agriculture",
            icon = "🌾",
            description = "The school focuses on smart agriculture, experiential learning, agricultural technology, food systems and sustainable farming.",
            head = "Prof. Sagar Maitra — Dean, MSSSoA",
            programs = listOf(
                "B.Sc. (Hons.) Agriculture",
                "M.Sc. (Hort.) Vegetable Science",
                "M.Sc. (Agri.) Entomology",
                "M.Sc. (Agri.) Agricultural Extension Education",
                "M.Sc. (Agri.) Genetics and Plant Breeding",
                "M.Sc. (Agri.) Plant Pathology",
                "M.Sc. (Agri.) Seed Science and Technology",
                "M.Sc. (Agri.) Agronomy",
                "M.Sc. (Agri.) Soil Science",
                "Certificate in Vermicomposting Farming",
                "Certificate in Poultry Farming",
                "Certificate in Organic Farming",
                "Certificate in Mushroom Farming",
                "Certificate in Dairy Farming",
                "Certificate in Bio fertilisers preparation"
            )
        )


        // ==================================================
        // MANAGEMENT
        // ==================================================

        "management" -> SchoolDetails(
            name = "School of Management",
            icon = "💼",
            description = "The School of Management focuses on experiential, applied and action learning with strong links to industry, entrepreneurship and production-based learning.",
            head = "Dr. Pramod Kumar Patjoshi — Professor and Associate Dean",
            programs = listOf(
                "Bachelor of Business Administration",
                "Bachelor of Commerce",
                "Bachelor of Business Administration (Healthcare Management)",
                "BBA Retail Management",
                "Bachelor of Science Animation and Multimedia",
                "MBA in Agribusiness Management",
                "MBA in Healthcare Management",
                "MBA with specialisation in Finance / HR / Marketing / DA",
                "MBA in Development Management"
            )
        )


        // ==================================================
        // FORENSIC
        // ==================================================

        "forensic" -> SchoolDetails(
            name = "School of Forensic Sciences",
            icon = "🔬",
            description = "The School of Forensic Sciences focuses on forensic science, cyber security and digital forensics, with practical and research-oriented training.",
            head = "Dr. Hirak Ranjan Dash — Associate Dean, SoFS",
            programs = listOf(
                "Diploma in Forensic Accounting and Fraud Investigation",
                "Diploma in Forensic DNA Analysis",
                "Bachelor of Science in Forensic Science",
                "Master of Science in Forensic Science",
                "Master of Science in Cyber Security & Digital Forensics",
                "Ph.D in Forensic Science"
            )
        )


        // ==================================================
        // LAW
        // ==================================================

        "law" -> SchoolDetails(
            name = "School of Law",
            icon = "⚖️",
            description = "The School of Law combines legal education with practical skill development and professional training.",
            head = "Dr. Pallab Das — Dean, SoL",
            programs = listOf(
                "BA. LL.B (Hons)",
                "BBA.LL.B (Hons)",
                "LLB",
                "LL.M. in Commercial Law",
                "LL.M. in Maritime Law",
                "LLM in Human Rights Law",
                "Ph.D in Law"
            )
        )


        // ==================================================
        // APPLIED SCIENCES
        // ==================================================

        "applied_sciences" -> SchoolDetails(
            name = "School of Applied Sciences",
            icon = "🧪",
            description = "The School of Applied Sciences offers science education across physics, chemistry, mathematics, botany and zoology with practical and research-oriented learning.",
            head = "Prof. (Dr.) Susanta Kumar Biswal — Director, School of Applied Sciences",
            programs = listOf(
                "B.Sc. Physics",
                "B.Sc. Chemistry",
                "B.Sc. Mathematics",
                "B.Sc. Botany",
                "B.Sc. Zoology",
                "M.Sc. Applied Physics",
                "M.Sc. Applied Chemistry",
                "M.Sc. Applied Mathematics",
                "M.Sc. Botany",
                "M.Sc. Zoology",
                "M.Sc. Geoinformatics",
                "Ph.D in Physics",
                "Ph.D in Chemistry",
                "Ph.D in Mathematics",
                "Ph.D in Botany",
                "Ph.D in Zoology",
                "Ph.D in Environmental Science"
            )
        )


        // ==================================================
        // PHARMACY
        // ==================================================

        "pharmacy" -> SchoolDetails(
            name = "School of Pharmacy & Life Sciences",
            icon = "💊",
            description = "The school focuses on pharmaceutical education, drug development, research, healthcare and entrepreneurship.",
            head = "Prof. (Dr.) Gurudutta Pattnaik — Dean",
            programs = listOf(
                "Diploma in Pharmacy",
                "Bachelor of Pharmacy",
                "Master of Pharmacy in Pharmaceutics",
                "Master of Pharmacy in Pharmaceutical Analysis",
                "Master of Pharmacy in Industrial Pharmacy",
                "Master of Pharmacy in Pharmaceutical Chemistry",
                "Master of Pharmacy in Pharmacology",
                "Master of Pharmacy in Regulatory Affairs",
                "Ph.D in Pharmacy"
            )
        )


        // ==================================================
        // AGRICULTURE & BIO-ENGINEERING
        // ==================================================

        "agri_bio" -> SchoolDetails(
            name = "School of Agriculture & Bio-Engineering",
            icon = "🌱",
            description = "The school integrates agricultural engineering, dairy technology and phytopharmaceuticals with hands-on learning and industrial collaboration.",
            head = "Dr. Santosh D. T. — Associate Dean, SoABE",
            programs = listOf(
                "B.Tech in Agricultural Engineering",
                "B.Tech in Dairy Technology",
                "Bachelor of Phytopharmaceuticals"
            )
        )


        // ==================================================
        // ALLIED HEALTH
        // ==================================================

        "allied_health" -> SchoolDetails(
            name = "School of Allied & Healthcare Sciences",
            icon = "🏥",
            description = "The School of Allied and Healthcare Sciences provides skill-based education and practical training in diagnostics, patient care and healthcare-related fields.",
            head = "Dr. Soumya Jal — Associate Professor and Dean, SoAHS",
            programs = listOf(
                "B.Sc. Medical Laboratory Technology",
                "B.Sc. Medical Radiation Technology",
                "B.Sc. Clinical Microbiology",
                "B.Sc. Optometry",
                "Bachelor of Physiotherapy",
                "M.Sc. Medical Laboratory Technology",
                "M.Sc. Applied and Clinical Microbiology",
                "M.Sc. Optometry",
                "Master of Public Health",
                "Diploma in Medical Laboratory Technology",
                "Diploma in Medical Radiation Technology",
                "Diploma in Community Health Practice",
                "Certificate in Ophthalmic Assistant",
                "Certificate in Ophthalmic Surgical Assistant",
                "Ph.D in Applied and Clinical Microbiology",
                "Ph.D in Medical Laboratory Technology"
            )
        )


        // ==================================================
        // BIOTECHNOLOGY
        // ==================================================

        "biotechnology" -> SchoolDetails(
            name = "School of Biotechnology",
            icon = "🧬",
            description = "The School of Biotechnology combines biological sciences with technological applications and emphasizes research, interdisciplinary learning and industry-focused training.",
            head = "Dr. Satyabrata Nanda — Dean, SoB",
            programs = listOf(
                "B.Tech in Bio-Technology Engineering",
                "B.Sc. (Honors) in Biotechnology",
                "M.Sc. in Biotechnology",
                "Ph.D. in Biotechnology"
            )
        )


        // ==================================================
        // BACHELOR STUDIES
        // ==================================================

        "bachelor" -> SchoolDetails(
            name = "School of Bachelor Studies",
            icon = "🎓",
            description = "The School of Bachelor Studies offers a flexible four-year multidisciplinary undergraduate programme aligned with the New Education Policy 2020, including multiple entry and exit options.",
            head = "School of Bachelor Studies",
            programs = listOf(
                "Four-Year Multidisciplinary Undergraduate Programme",
                "Undergraduate Certificate after Year 1",
                "Undergraduate Diploma after Year 2",
                "Bachelor's Degree after Year 3",
                "Bachelor's Degree with Honours / Research after Year 4"
            )
        )


        // ==================================================
        // VETERINARY
        // ==================================================

        "veterinary" -> SchoolDetails(
            name = "School of Veterinary & Animal Sciences",
            icon = "🐾",
            description = "The School of Veterinary and Animal Sciences focuses on veterinary education, animal health, food safety, research and One Health-oriented learning.",
            head = "Prof. (Capt.) B Suresh Subramonian — Dean, SoVAS",
            programs = listOf(
                "Bachelor of Veterinary Science and Animal Husbandry (B.V.Sc. & A.H.)"
            )
        )


        // ==================================================
        // NURSING
        // ==================================================

        "nursing" -> SchoolDetails(
            name = "School of Nursing",
            icon = "🩺",
            description = "The School of Nursing provides skill-based nursing education with practical clinical training, community health activities and healthcare partnerships.",
            head = "Prof. Sunil Kumar Jha — Director, SoAHS & SoN",
            programs = listOf(
                "GNM (General Nursing in Midwifery)",
                "ANM (Auxiliary Nursing Midwifery)",
                "B.Sc. Nursing"
            )
        )


        // ==================================================
        // DESIGN
        // ==================================================

        "design" -> SchoolDetails(
            name = "School of Design Studies",
            icon = "🎨",
            description = "The School of Design Studies focuses on creative intelligence, technology, human-centred innovation and industry-relevant design education.",
            head = "Prof. Ratheesh Nair — Director, SoDS, BBSR",
            programs = listOf(
                "Bachelor of Design in Automobile & Transportation Design",
                "B.Des in Product Design",
                "Bachelor of Design in Graphic / Visual Communication Design",
                "Bachelor of Design in UI/UX Design"
            )
        )


        // ==================================================
        // VOCATIONAL
        // ==================================================

        "vocational" -> SchoolDetails(
            name = "School of Vocational Education & Training",
            icon = "🛠️",
            description = "The School of Vocational Education and Training integrates skill education with practical production-based learning and multiple education pathways.",
            head = "Prof. Mir Sadat Ali — Dean, Parlakhemundi",
            programs = listOf(
                "Diploma in Civil Engineering",
                "Diploma in Computer Science Engineering",
                "Diploma in Electrical Engineering",
                "Diploma in Mechanical Engineering",
                "Diploma in Automobile Engineering",
                "ITI in Automobile",
                "Diploma in Vocational in Automobile",
                "Diploma in Vocational in Manufacturing",
                "Diploma in Vocational in Electrical Maintenance",
                "Diploma in Electronics and Communication Engineering",
                "Diploma in Mining Engineering",
                "Bachelor of Vocational (Manufacturing)",
                "Bachelor of Vocational (Electrical Maintenance)"
            )
        )


        else -> SchoolDetails(
            name = "Department",
            icon = "🏢",
            description = "School information is not available.",
            head = "CUTM",
            programs = emptyList()
        )
    }
}


@Composable
fun SchoolDetailScreen(
    schoolId: String
) {

    val school = getSchoolDetails(schoolId)


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(0xFFF3FBFD)
            ),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(
            bottom = 25.dp
        )
    ) {

        item {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
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
                        bottom = 30.dp
                    )
            ) {

                Text(
                    text = school.icon,
                    fontSize = 30.sp
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = school.name,
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "CUTM School Information",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 14.sp
                )
            }
        }


        item {

            DetailSection(
                title = "About the School",
                content = school.description
            )
        }


        item {

            DetailSection(
                title = "School Head",
                content = school.head
            )
        }


        item {

            Text(
                text = "Programs",
                modifier = Modifier.padding(
                    start = 22.dp,
                    end = 22.dp,
                    top = 5.dp,
                    bottom = 10.dp
                ),
                color = Color(0xFF07516A),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }


        items(school.programs) { program ->

            ProgramCard(
                program = program
            )
        }
    }
}


// ==========================================================
// DETAIL SECTION
// ==========================================================

@Composable
private fun DetailSection(
    title: String,
    content: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 22.dp,
                vertical = 8.dp
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
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = title,
                color = Color(0xFF07516A),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = content,
                color = Color(0xFF4B5F64),
                fontSize = 14.sp,
                lineHeight = 21.sp
            )
        }
    }
}


// ==========================================================
// PROGRAM CARD
// ==========================================================

@Composable
private fun ProgramCard(
    program: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 22.dp,
                vertical = 5.dp
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {

            Text(
                text = "•",
                color = Color(0xFF08758A),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Text(
                text = program,
                modifier = Modifier.weight(1f),
                color = Color(0xFF334B52),
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }
    }
}