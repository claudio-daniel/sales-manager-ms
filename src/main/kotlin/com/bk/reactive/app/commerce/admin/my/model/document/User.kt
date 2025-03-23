package com.bk.reactive.app.commerce.admin.my.model.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class User(
    @Id
    val id: String,
    val name: String,
    val lastName: String,
    val userName: String,
    val password: String,
    val email: String,
    val address: String
    //val userRoles: Set<UserRole>;
)