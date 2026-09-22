package com.uniguide.app.ui.teacher.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TeacherLoginScreen() {

    var teacherId by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF5F9FF),
                        Color.White,
                        Color(0xFFF8FAFF)
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 24.dp,
                    vertical = 45.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "👨‍🏫",
                fontSize = 50.sp
            )

            Spacer(
                modifier = Modifier.height(15.dp)
            )

            Text(
                text = "Teacher Login",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF2563EB)
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Welcome back to UniGuide",
                fontSize = 14.sp,
                color = Color(0xFF64748B)
            )

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp)
                ) {

                    Text(
                        text = "Sign in to Teacher Portal",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF111827)
                    )

                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )

                    OutlinedTextField(
                        value = teacherId,
                        onValueChange = {
                            teacherId = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Teacher ID")
                        },
                        leadingIcon = {
                            Text("👨‍🏫")
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = Color(0xFF2563EB),
                            unfocusedIndicatorColor = Color(0xFFE1E7EF)
                        )
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Password")
                        },
                        leadingIcon = {
                            Text("🔒")
                        },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = Color(0xFF2563EB),
                            unfocusedIndicatorColor = Color(0xFFE1E7EF)
                        )
                    )

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFF2563EB),
                                        Color(0xFF4F8EF7)
                                    )
                                ),
                                shape = RoundedCornerShape(18.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "Login  →",
                            color = Color.White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(25.dp)
            )

            Text(
                text = "Teacher Portal • UniGuide",
                fontSize = 13.sp,
                color = Color(0xFF94A3B8)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TeacherLoginScreenPreview() {
    TeacherLoginScreen()
}