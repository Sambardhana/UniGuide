package com.uniguide.app.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

private val ChatTeal = Color(0xFF087D8D)
private val ChatBackground = Color(0xFFF0FAFC)
private val AiBubble = Color.White
private val UserBubble = Color(0xFF087D8D)
private val SendPurple = Color(0xFF7050A5)

private data class DisplayMessage(
    val text: String,
    val isUser: Boolean
)

@Composable
fun ChatScreen(viewModel: ChatViewModel = viewModel()) {
    var textInput by remember { mutableStateOf("") }
    val aiResponse = viewModel.chatResponseState
    val isLoading = viewModel.isLoading

    // Keep the conversation visible in chronological order.
    val conversation = remember { mutableStateListOf<DisplayMessage>() }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    // Add each new AI response to the conversation.
    LaunchedEffect(aiResponse) {
        if (
            aiResponse.isNotBlank() &&
            aiResponse != "Ready to chat with UniGuide AI!" &&
            aiResponse != "Thinking..."
        ) {
            val lastMessage = conversation.lastOrNull()

            // Avoid displaying the same response more than once.
            if (lastMessage?.isUser != false ||
                lastMessage.text != formatAiResponse(aiResponse)
            ) {
                conversation.add(
                    DisplayMessage(
                        text = formatAiResponse(aiResponse),
                        isUser = false
                    )
                )
            }

            if (conversation.isNotEmpty()) {
                listState.animateScrollToItem(conversation.lastIndex)
            }
        }
    }

    // Scroll when loading starts so the latest question stays visible.
    LaunchedEffect(isLoading) {
        if (isLoading && conversation.isNotEmpty()) {
            listState.animateScrollToItem(conversation.lastIndex)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ChatBackground)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(ChatTeal)
                .padding(horizontal = 20.dp, vertical = 22.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "🤖 UniGuide AI",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // User and AI messages
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(conversation) { _, message ->
                ChatBubble(
                    text = message.text,
                    isUser = message.isUser
                )
            }

            if (isLoading) {
                item {
                    ChatBubble(
                        text = "Thinking...",
                        isUser = false
                    )
                }
            }
        }

        // Input and Send button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(ChatBackground)
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 16.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = textInput,
                onValueChange = { textInput = it },
                modifier = Modifier.weight(1f),
                placeholder = {
                    Text(
                        text = "Ask UniGuide AI...",
                        color = Color.Gray
                    )
                },
                textStyle = androidx.compose.ui.text.TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = ChatTeal,
                    focusedBorderColor = SendPurple,
                    unfocusedBorderColor = SendPurple,
                    focusedContainerColor = ChatBackground,
                    unfocusedContainerColor = ChatBackground
                ),
                shape = RoundedCornerShape(8.dp),
                enabled = !isLoading,
                maxLines = 3
            )

            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = {
                    val message = textInput.trim()

                    if (message.isNotEmpty() && !isLoading) {
                        // Show the user's question immediately.
                        conversation.add(
                            DisplayMessage(
                                text = message,
                                isUser = true
                            )
                        )

                        textInput = ""

                        scope.launch {
                            if (conversation.isNotEmpty()) {
                                listState.animateScrollToItem(
                                    conversation.lastIndex
                                )
                            }
                        }

                        viewModel.sendMessage(message)
                    }
                },
                enabled = textInput.isNotBlank() && !isLoading,
                modifier = Modifier.heightIn(min = 52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SendPurple,
                    contentColor = Color.White,
                    disabledContainerColor = SendPurple.copy(alpha = 0.45f),
                    disabledContentColor = Color.White.copy(alpha = 0.7f)
                ),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = "Send",
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
private fun ChatBubble(
    text: String,
    isUser: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) {
            Arrangement.End
        } else {
            Arrangement.Start
        }
    ) {
        Surface(
            color = if (isUser) UserBubble else AiBubble,
            shape = RoundedCornerShape(22.dp),
            modifier = Modifier.widthIn(max = 320.dp)
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 14.dp
                ),
                color = if (isUser) Color.White else Color.Black,
                fontSize = 16.sp,
                lineHeight = 23.sp
            )
        }
    }
}

private fun formatAiResponse(text: String): String {
    return text
        .replace(
            Regex("<br\\s*/?>", RegexOption.IGNORE_CASE),
            "\n"
        )
        .replace(
            Regex("</(p|div|h[1-6]|tr|li)\\s*>", RegexOption.IGNORE_CASE),
            "\n"
        )
        .replace(Regex("<[^>]+>"), "")
        .replace(Regex("(?m)^\\s{0,3}#{1,6}\\s*"), "")
        .replace("**", "")
        .replace("__", "")
        .replace(Regex("(?<!\\*)\\*(?!\\*)"), "")
        .replace("~~", "")
        .replace(
            Regex("(?m)^\\s*\\|?\\s*:?-{3,}:?\\s*(\\|\\s*:?-{3,}:?\\s*)+\\|?\\s*$"),
            ""
        )
        .replace(Regex("[ \\t]+\\n"), "\n")
        .replace(Regex("\\n{3,}"), "\n\n")
        .trim()
}