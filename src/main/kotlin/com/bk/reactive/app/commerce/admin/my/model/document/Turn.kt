package com.bk.reactive.app.commerce.admin.my.model.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.ZonedDateTime

@Document(collection = "turns")
data class Turn(
    @Id
    val id: String,
    val name: String,
    val description: String,
    val startDate: ZonedDateTime,
    val endDate: ZonedDateTime
)