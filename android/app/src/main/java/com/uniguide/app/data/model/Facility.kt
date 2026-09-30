package com.uniguide.app.data.model

data class Facility(
    val id: Long,
    val name: String,
    val type: String?,
    val description: String?,
    val openingHours: String?,
    val contactNumber: String?,
    val locationId: Long?,
    val locationName: String?,
    val createdAt: String?,
    val updatedAt: String?
)