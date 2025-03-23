package com.bk.reactive.app.commerce.admin.my.model.api.dto

class ProductDTO(
    val id: Long,
    val name: String,
    val code: String,
    val productTypeId: Long,
    val price: Double,
    val count: Int
)