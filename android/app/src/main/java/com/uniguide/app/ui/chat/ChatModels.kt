package com.uniguide.app.ui.chat

data class ChatMessage(
    val role: String,
    val content: String
)

data class ChatRequest(
    val role: String,
    val messages: List<ChatMessage>
)

data class ChatResponse(
    val reply: String
)