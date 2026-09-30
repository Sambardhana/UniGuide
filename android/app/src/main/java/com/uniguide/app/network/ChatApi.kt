package com.uniguide.app.network

import com.uniguide.app.ui.chat.ChatRequest
import com.uniguide.app.ui.chat.ChatResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatApi {

    @POST("api/ai/chat")
    suspend fun sendMessage(
        @Body request: ChatRequest
    ): ChatResponse
}