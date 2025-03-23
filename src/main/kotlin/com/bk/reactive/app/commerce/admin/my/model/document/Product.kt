package com.bk.reactive.app.commerce.admin.my.model.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "products")
data class Product(
    @Id
    val id: String,
    val name: String,
    val price: Double,
    val code: String,
    val productType: ProductType,
    val toppings: List<String> = arrayListOf()
)