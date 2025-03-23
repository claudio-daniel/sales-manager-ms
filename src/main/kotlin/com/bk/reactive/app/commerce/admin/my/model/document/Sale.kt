package com.bk.reactive.app.commerce.admin.my.model.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.ZonedDateTime


@Document(collection = "sales")
data class Sale(
    @Id
    val id: String?,
    val order: Order,
    val client: Client,
    val date: ZonedDateTime,
    val paymentType: PaymentType,
    val cash: Cash,
    val payment: Double,
    val returned: Double,
    val total: Double
)