package com.uniguide.app.data.model

data class Contact(
    val id: Long,
    val name: String,
    val designation: String? = null,
    val category: String? = null,
    val phoneNumber: String,
    val email: String? = null,
    val officeLocation: String? = null,
    val department: Department? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
)
