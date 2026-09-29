package com.uniguide.app.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uniguide.app.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private val _messages =
        MutableStateFlow<List<ChatMessage>>(emptyList())

    val messages: StateFlow<List<ChatMessage>> =
        _messages

    private val _isLoading =
        MutableStateFlow(false)

    val isLoading: StateFlow<Boolean> =
        _isLoading

    fun sendMessage(
        message: String,
        userRole: String
    ) {

        if (message.isBlank()) return

        val userMessage = ChatMessage(
            role = "user",
            content = message
        )

        _messages.value =
            _messages.value + userMessage

        viewModelScope.launch {

            _isLoading.value = true

            try {

                val response =
                    RetrofitClient.chatApi.sendMessage(

                        ChatRequest(
                            role = userRole,
                            messages = _messages.value
                        )
                    )

                val aiMessage = ChatMessage(
                    role = "assistant",
                    content = response.reply
                )

                _messages.value =
                    _messages.value + aiMessage

            } catch (e: Exception) {

                val errorMessage = ChatMessage(
                    role = "assistant",
                    content =
                        "Sorry, I couldn't connect to the UniGuide AI server."
                )

                _messages.value =
                    _messages.value + errorMessage
            }

            _isLoading.value = false
        }
    }
}