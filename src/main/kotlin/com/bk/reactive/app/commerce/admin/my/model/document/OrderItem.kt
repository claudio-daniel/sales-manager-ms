package com.bk.reactive.app.commerce.admin.my.model.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "order_items")
data class OrderItem(
    @Id
    val id: String?,
    val orderId: String?,
    val product: Product,
    val count: Int
)