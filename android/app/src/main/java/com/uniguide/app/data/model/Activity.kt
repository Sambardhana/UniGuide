package com.uniguide.app.data.model

data class Activity(
    val id: Long,
    val name: String,
    val category: String? = null,
    val description: String? = null,
    val coordinatorName: String? = null,
    val coordinatorContact: String? = null,
    val location: CampusLocation? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
)
