package com.bk.reactive.app.commerce.admin.my.model.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "clients")
data class Client(
    @Id
    val id: String?,
    val name: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val dni: String
)