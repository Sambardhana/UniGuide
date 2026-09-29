package com.uniguide.app.data.model

data class Notice(
    val id: Long,
    val title: String,
    val content: String,
    val category: String? = null,
    val attachmentUrl: String? = null,
    val isPinned: Boolean? = false,
    val publishedAt: String? = null,
    val author: UserSummary? = null,
    val department: Department? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
)

data class UserSummary(
    val id: Long? = null,
    val username: String? = null,
    val name: String? = null
)
