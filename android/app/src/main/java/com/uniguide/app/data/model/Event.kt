package com.uniguide.app.data.model

data class Event(
    val id: Long,
    val title: String,
    val description: String?,
    val startDate: String,
    val endDate: String?,
    val venue: String?,
    val organizer: String?,
    val registrationLink: String?,
    val locationId: Long?,
    val locationName: String?,
    val createdAt: String?,
    val updatedAt: String?
)