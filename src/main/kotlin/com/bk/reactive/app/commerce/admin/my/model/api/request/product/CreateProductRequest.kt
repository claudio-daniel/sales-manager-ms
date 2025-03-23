package com.bk.reactive.app.commerce.admin.my.model.api.request.product

class CreateProductRequest(
    val id: Long,
    val name: String,
    val price: Double,
    val code: String,
    val productType: Long
)