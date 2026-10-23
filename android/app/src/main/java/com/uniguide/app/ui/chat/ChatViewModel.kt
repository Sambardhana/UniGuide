package com.uniguide.app.ui.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.uniguide.app.network.ChatMessage
import com.uniguide.app.network.ChatRequest
import com.uniguide.app.network.ChatResponse
import com.uniguide.app.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatViewModel : ViewModel() {

    var chatResponseState by mutableStateOf(
        "Ready to chat with UniGuide AI!"
    )
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun sendMessage(userMessage: String) {
        if (userMessage.isBlank() || isLoading) return

        chatResponseState = "Thinking..."
        isLoading = true

        val request = ChatRequest(
            messages = listOf(
                ChatMessage(
                    role = "user",
                    content = userMessage.trim()
                )
            )
        )

        RetrofitClient.chatApi.sendChatMessage(request)
            .enqueue(object : Callback<ChatResponse> {

                override fun onResponse(
                    call: Call<ChatResponse>,
                    response: Response<ChatResponse>
                ) {
                    isLoading = false

                    if (response.isSuccessful) {
                        val reply = response.body()?.reply

                        chatResponseState = if (!reply.isNullOrBlank()) {
                            reply
                        } else {
                            "The server returned an empty reply."
                        }
                    } else {
                        val errorDetails = response.errorBody()
                            ?.string()
                            ?.takeIf { it.isNotBlank() }

                        chatResponseState = buildString {
                            append("Server error: ${response.code()}")
                            if (errorDetails != null) {
                                append("\n")
                                append(errorDetails)
                            }
                        }
                    }
                }

                override fun onFailure(
                    call: Call<ChatResponse>,
                    t: Throwable
                ) {
                    isLoading = false
                    chatResponseState =
                        "Connection failed: ${
                            t.localizedMessage ?: "Unable to connect to the server."
                        }"
                }
            })
    }
}