package com.bk.reactive.app.commerce.admin.my.repository

import com.bk.reactive.app.commerce.admin.my.model.document.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository : ReactiveMongoRepository<User, String> {
    fun findByUserName(userName: String): Mono<User>
}