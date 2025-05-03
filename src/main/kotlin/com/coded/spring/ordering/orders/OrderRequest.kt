package com.coded.spring.com.coded.spring.ordering.orders

data class OrderRequest(
    val userId: Long,
    val restaurant: String,
    val items: List<Item>
)

data class Item(
    val name: String,
    val quantity: Int
)