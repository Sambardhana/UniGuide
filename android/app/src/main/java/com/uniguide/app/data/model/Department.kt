package com.uniguide.app.data.model

data class Department(
    val id: Long,
    val name: String,
    val code: String,
    val description: String?,
    val contactEmail: String?,
    val contactPhone: String?,
    val locationId: Long?,
    val locationName: String?,
    val createdAt: String?,
    val updatedAt: String?
)