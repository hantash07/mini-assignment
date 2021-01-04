package com.hantash.mini_assignment.model

data class Menu(
    val id: Long,
    val categoryId: Long,
    val name: String,
    val description: String,
    val price: Int,
    val image: Int,
    val isAdded: Boolean
)