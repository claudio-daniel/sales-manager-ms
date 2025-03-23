package com.bk.reactive.app.commerce.admin.my.model.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "order_types")
data class OrderType(
    @Id
    val id: String,
    val name: String,
    val description: String
)