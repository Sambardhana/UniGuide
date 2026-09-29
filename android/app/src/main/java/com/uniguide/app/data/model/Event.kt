package com.uniguide.app.data.model

data class Event(
    val id: Long,
    val title: String,
    val description: String? = null,
    val eventDate: String? = null,
    val location: String? = null
)
