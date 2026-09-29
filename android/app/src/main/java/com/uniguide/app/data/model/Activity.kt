package com.uniguide.app.data.model

data class Activity(
    val id: Long,
    val name: String,
    val category: String?,
    val description: String?,
    val coordinatorName: String?,
    val coordinatorContact: String?,
    val locationId: Long?,
    val locationName: String?,
    val createdAt: String?,
    val updatedAt: String?
)