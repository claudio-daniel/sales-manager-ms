package com.bk.reactive.app.commerce.admin.my.model.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.ZonedDateTime

@Document(collection = "cashes")
data class Cash(
    @Id
    val id: String? = null,
    val turn: Turn,
    val user: User,
    val cashStatus: CashStatus,
    val startMoney: Double,
    val endMoney: Double? = null,
    var partialBalance: Double = 0.0,
    val startDate: ZonedDateTime,
    val endDate: ZonedDateTime? = null
)