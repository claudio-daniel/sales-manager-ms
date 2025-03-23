package com.bk.reactive.app.commerce.admin.my.model.api.response

class OrderResponse(
    val id: Long,
    val orderItems: Set<OrderItemResponse>,
    val orderType: String,
    val total: Double
)