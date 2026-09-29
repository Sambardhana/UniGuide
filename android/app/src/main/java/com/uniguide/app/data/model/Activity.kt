package com.uniguide.app.data.model

data class Activity(
    val id: Long,
    val name: String,
    val description: String? = null,
    val category: String? = null
)
