package com.uniguide.app.data.model

data class Hostel(
    val id: Long,
    val name: String,
    val type: String?,
    val capacity: Int?,
    val wardenName: String?,
    val wardenContact: String?,
    val description: String?,
    val locationId: Long?,
    val locationName: String?,
    val createdAt: String?,
    val updatedAt: String?
)