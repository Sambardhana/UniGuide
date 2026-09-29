package com.uniguide.app.data.model

data class Hostel(
    val id: Long,
    val name: String,
    val type: String? = null,
    val capacity: Int? = null,
    val wardenName: String? = null,
    val wardenContact: String? = null,
    val description: String? = null,
    val location: CampusLocation? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
)
