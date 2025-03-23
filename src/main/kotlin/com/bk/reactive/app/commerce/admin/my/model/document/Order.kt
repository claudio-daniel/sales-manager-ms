package com.bk.reactive.app.commerce.admin.my.model.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "orders")
data class Order(
    @Id
    val id: String?,
    val orderItems: List<OrderItem>,
    val orderType: OrderType
)