package com.bk.reactive.app.commerce.admin.my.model.api.dto

class DishDTO(
    val id: Long,
    val mainDish: ProductDTO,
    val toppings: Set<ProductDTO>
)

