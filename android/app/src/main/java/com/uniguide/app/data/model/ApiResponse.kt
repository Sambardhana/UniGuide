package com.uniguide.app.data.model

data class ApiResponse<T>(
    val value: List<T> = emptyList(),
    val Count: Int = 0
)