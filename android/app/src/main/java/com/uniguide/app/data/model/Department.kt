package com.uniguide.app.data.model

data class Department(
    val id: Long,
    val name: String,
    val code: String,
    val description: String? = null,
    val contactEmail: String? = null,
    val contactPhone: String? = null,
    val location: CampusLocation? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
)
