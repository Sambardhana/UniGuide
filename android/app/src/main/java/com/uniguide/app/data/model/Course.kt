package com.uniguide.app.data.model

data class Course(
    val id: Long,
    val code: String,
    val title: String,
    val description: String?,
    val credits: Int?,
    val semester: Int?,
    val departmentId: Long?,
    val departmentName: String?,
    val createdAt: String?,
    val updatedAt: String?
)