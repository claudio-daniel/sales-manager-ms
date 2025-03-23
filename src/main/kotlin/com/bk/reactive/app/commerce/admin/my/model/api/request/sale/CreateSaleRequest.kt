package com.bk.reactive.app.commerce.admin.my.model.api.request.sale

import com.bk.reactive.app.commerce.admin.my.model.api.request.client.CreateClientRequest
import com.bk.reactive.app.commerce.admin.my.model.api.request.order.CreateOrderRequest

class CreateSaleRequest(
    val createOrderRequest: CreateOrderRequest,
    val userId: String,
    val createClientRequest: CreateClientRequest,
    val paymentTypeId: String,
    val cashId: String,
    val payment: Double,
    val returned: Double,
    val total: Double
)