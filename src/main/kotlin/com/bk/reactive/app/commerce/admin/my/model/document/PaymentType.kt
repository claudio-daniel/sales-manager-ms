package com.bk.reactive.app.commerce.admin.my.model.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "payment_types")
data class PaymentType(
    @Id
    val id: String,
    val name: String,
    val description: String
)