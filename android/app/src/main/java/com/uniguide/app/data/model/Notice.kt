package com.uniguide.app.data.model

data class Notice(
    val id: Long,
    val title: String,
    val content: String? = null,
    val category: String? = null,
    val pinned: Boolean = false
)
