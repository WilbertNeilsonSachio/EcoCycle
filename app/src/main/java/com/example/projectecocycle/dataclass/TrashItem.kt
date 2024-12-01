package com.example.projectecocycle.dataclass

import java.io.Serializable

data class TrashItem(
    val imageResId: Int,
    val name: String,
    val price: String,
    val unit: String,
    val tag: String
): Serializable
