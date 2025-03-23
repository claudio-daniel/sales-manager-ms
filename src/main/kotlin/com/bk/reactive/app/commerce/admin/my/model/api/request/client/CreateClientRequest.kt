package com.bk.reactive.app.commerce.admin.my.model.api.request.client

class CreateClientRequest(
    val id: Long,
    val name: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val dni: String
)