package com.uniguide.app.data.model

data class Facility(
    val id: Long,
    val name: String,
    val description: String? = null,
    val location: String? = null
)
