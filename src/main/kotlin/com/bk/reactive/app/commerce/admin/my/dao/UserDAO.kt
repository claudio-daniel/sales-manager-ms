package com.bk.reactive.app.commerce.admin.my.dao

import com.bk.reactive.app.commerce.admin.my.model.document.User
import com.bk.reactive.app.commerce.admin.my.repository.UserRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class UserDAO(
    private val userRepository: UserRepository
) {
    fun findAll(): Flux<User> = userRepository.findAll()

    fun findById(id: String): Mono<User> = userRepository.findById(id)

    fun getUserById(id: String): Mono<User> = findById(id)

    fun findByUserName(userName: String): Mono<User> = userRepository.findByUserName(userName)
}