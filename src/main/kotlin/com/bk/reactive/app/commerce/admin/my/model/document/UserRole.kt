package com.bk.reactive.app.commerce.admin.my.model.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users_roles")
data class UserRole(
    @Id
    val id: String,
    val userId: Long,
    val roleId: Long
)