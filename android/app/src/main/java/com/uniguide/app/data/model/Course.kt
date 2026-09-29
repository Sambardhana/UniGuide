package com.uniguide.app.data.model

data class Course(
    val id: Long,
    val name: String,
    val code: String? = null,
    val description: String? = null,
    val departmentId: Long? = null
)
