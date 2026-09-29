package com.uniguide.app.data.model

data class Contact(
    val id: Long,
    val name: String,
    val phone: String? = null,
    val email: String? = null,
    val category: String? = null,
    val description: String? = null
)
