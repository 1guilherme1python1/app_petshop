package com.example.petshopecommerce.model

data class Item(
    val description: String = "",
    val picUrl: List<String> = listOf(),
    val price: Double = 0.0,
    val rating: Double = 0.0,
    val sellerName: String = "",
    val sellerPic: String = "",
    val sellerTell: Int = 0,
    val size: List<String> = listOf(),
    val title: String = ""
)
