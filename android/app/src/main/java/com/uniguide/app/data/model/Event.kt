package com.uniguide.app.data.model

data class Event(
    val id: Long,
    val title: String,
    val description: String? = null,
    val startDate: String,
    val endDate: String? = null,
    val venue: String? = null,
    val organizer: String? = null,
    val registrationLink: String? = null,
    val location: CampusLocation? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
)
