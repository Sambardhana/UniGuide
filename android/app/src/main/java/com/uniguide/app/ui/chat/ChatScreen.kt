package com.uniguide.app.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ChatScreen(
    userRole: String,
    viewModel: ChatViewModel = viewModel()
) {

    val messages by viewModel.messages.collectAsState()

    val isLoading by viewModel.isLoading.collectAsState()

    var messageText by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3FBFD))
    ) {

        // Header

        Text(
            text = "🤖 UniGuide AI",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF08758A))
                .padding(20.dp),
            color = Color.White
        )

        // Messages

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(12.dp),

            verticalArrangement =
                Arrangement.spacedBy(10.dp)
        ) {

            items(messages) { message ->

                ChatBubble(
                    message = message
                )
            }

            if (isLoading) {

                item {

                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(10.dp)
                    )
                }
            }
        }

        // Input area

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            OutlinedTextField(

                value = messageText,

                onValueChange = {
                    messageText = it
                },

                modifier = Modifier
                    .weight(1f),

                placeholder = {
                    Text("Ask UniGuide AI...")
                },

                singleLine = false
            )

            Button(

                onClick = {

                    if (messageText.isNotBlank()) {

                        viewModel.sendMessage(
                            message = messageText,
                            userRole = userRole
                        )

                        messageText = ""
                    }
                },

                modifier = Modifier
                    .padding(start = 8.dp)
            ) {

                Text("Send")
            }
        }
    }
}


@Composable
private fun ChatBubble(
    message: ChatMessage
) {

    val isUser =
        message.role == "user"

    Row(
        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement =
            if (isUser)
                Arrangement.End
            else
                Arrangement.Start
    ) {

        Text(
            text = message.content,

            modifier = Modifier
                .background(
                    if (isUser)
                        Color(0xFF08758A)
                    else
                        Color.White,

                    RoundedCornerShape(16.dp)
                )
                .padding(14.dp),

            color =
                if (isUser)
                    Color.White
                else
                    Color.Black
        )
    }
}