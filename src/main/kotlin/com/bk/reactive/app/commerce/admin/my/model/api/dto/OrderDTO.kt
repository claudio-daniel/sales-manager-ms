package com.bk.reactive.app.commerce.admin.my.model.api.dto

class OrderDTO(
    val id: Long,
    val dishes: Set<DishDTO>,
    val orderTypeId: Long
)
