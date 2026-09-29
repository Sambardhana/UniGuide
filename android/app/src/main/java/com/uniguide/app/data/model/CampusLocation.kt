package com.uniguide.app.data.model

data class CampusLocation(
    val id: Long,
    val name: String,
    val code: String? = null,
    val category: String? = null,
    val description: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val floorCount: Int? = null,
    val qrCodeKey: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
)
