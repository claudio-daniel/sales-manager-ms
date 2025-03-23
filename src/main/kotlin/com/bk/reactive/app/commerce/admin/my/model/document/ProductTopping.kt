package com.bk.reactive.app.commerce.admin.my.model.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "product_topping")
data class ProductTopping(
    @Id
    val id: String,
    val mainDishId: Long,
    val toppingId: Long
)