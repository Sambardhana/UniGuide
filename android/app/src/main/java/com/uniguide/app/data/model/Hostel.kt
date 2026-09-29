package com.uniguide.app.data.model

data class Hostel(
    val id: Long,
    val name: String,
    val description: String? = null,
    val location: String? = null,
    val capacity: Int? = null
)
