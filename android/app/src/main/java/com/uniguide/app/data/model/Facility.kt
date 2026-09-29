package com.uniguide.app.data.model

data class Facility(
    val id: Long,
    val name: String,
    val type: String? = null,
    val description: String? = null,
    val openingHours: String? = null,
    val contactNumber: String? = null,
    val location: CampusLocation? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
)
