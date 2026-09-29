package com.uniguide.app.data.model

data class CampusLocation(
    val id: Long,
    val name: String,
    val description: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val qrCodeKey: String? = null
)
