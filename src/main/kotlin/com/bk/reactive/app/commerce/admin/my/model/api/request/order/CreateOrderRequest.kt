package com.bk.reactive.app.commerce.admin.my.model.api.request.order

class CreateOrderRequest(
    val createOrderItemsRequest: Set<CreateOrderItemsRequest>,
    val orderTypeId: String
)