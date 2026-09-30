package com.uniguide.app.data.model

data class CampusLocation(
    val id: Long,
    val name: String,
    val code: String?,
    val category: String?,
    val description: String?,
    val latitude: Double?,
    val longitude: Double?,
    val floorCount: Int?,
    val qrCodeKey: String?,
    val createdAt: String?,
    val updatedAt: String?
)