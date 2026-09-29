package com.uniguide.app.data.model

data class Notice(
    val id: Long,
    val title: String,
    val content: String,
    val category: String?,
    val attachmentUrl: String?,
    val isPinned: Boolean?,
    val publishedAt: String?,
    val authorId: Long?,
    val authorName: String?,
    val departmentId: Long?,
    val departmentName: String?,
    val createdAt: String?,
    val updatedAt: String?
)