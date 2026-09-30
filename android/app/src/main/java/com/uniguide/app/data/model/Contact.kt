package com.uniguide.app.data.model

data class Contact(
    val id: Long,
    val name: String,
    val designation: String?,
    val category: String?,
    val phoneNumber: String,
    val email: String?,
    val officeLocation: String?,
    val departmentId: Long?,
    val departmentName: String?,
    val createdAt: String?,
    val updatedAt: String?
)