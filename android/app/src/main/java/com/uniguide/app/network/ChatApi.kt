package com.uniguide.app.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatApi {

    @POST("api/ai/chat")
    fun sendChatMessage(@Body request: ChatRequest): Call<ChatResponse>
}

data class ChatRequest(
    val messages: List<ChatMessage>
)

data class ChatMessage(
    val role: String,
    val content: String
)

data class ChatResponse(
    val reply: String
)