package com.uniguide.app.data.model

data class Course(
    val id: Long,
    val code: String,
    val title: String,
    val description: String? = null,
    val credits: Int? = null,
    val semester: Int? = null,
    val department: Department? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
)
