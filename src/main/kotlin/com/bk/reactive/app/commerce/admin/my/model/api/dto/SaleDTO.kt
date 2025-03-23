package com.bk.reactive.app.commerce.admin.my.model.api.dto

class SaleDTO(
    val id: Long,
    val orderDto: OrderDTO,
    val userId: Long,
    val clientDto: ClientDTO,
    val paymentTypeId: Long,
    val cashId: Long,
    val payment: Double,
    val returned: Double,
    val total: Double
)