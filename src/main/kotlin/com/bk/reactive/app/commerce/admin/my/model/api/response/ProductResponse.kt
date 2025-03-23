package com.bk.reactive.app.commerce.admin.my.model.api.response

class ProductResponse(
    val id: String,
    val code: String,
    val name: String,
    val price: Double,
    val productType: ProductTypeResponse
)