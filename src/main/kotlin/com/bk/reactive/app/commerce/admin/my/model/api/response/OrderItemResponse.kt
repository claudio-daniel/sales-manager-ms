package com.bk.reactive.app.commerce.admin.my.model.api.response

class OrderItemResponse(
    val id: Long,
    val product: ProductResponse,
    val count: Int
)