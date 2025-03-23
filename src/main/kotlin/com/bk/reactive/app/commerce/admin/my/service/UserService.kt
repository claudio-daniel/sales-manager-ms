package com.bk.reactive.app.commerce.admin.my.service

import com.bk.reactive.app.commerce.admin.my.dao.UserDAO
import com.bk.reactive.app.commerce.admin.my.exception.api.ApplicationException
import com.bk.reactive.app.commerce.admin.my.model.api.response.UserResponse
import com.bk.reactive.app.commerce.admin.my.model.mapper.UserMapper.Companion.fromUserToUserResponse
import mu.KotlinLogging
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserService(
    private val userDAO: UserDAO
) {
    private val logger = KotlinLogging.logger { }

    fun findAll(): Flux<UserResponse> = userDAO.findAll()
        .map { fromUserToUserResponse(it) }

    fun findByUserName(userName: String): Mono<UserResponse> {
        return userDAO.findByUserName(userName)
            .map { fromUserToUserResponse(it) }
            .onErrorResume {
                logger.error { it.localizedMessage }
                Mono.error {
                    ApplicationException(message = it.localizedMessage, cause = Throwable(it.localizedMessage))
                }
            }

    }

    fun getUserResponseById(id: String): Mono<UserResponse> {
        return userDAO.getUserById(id)
            .map { fromUserToUserResponse(it) }
            .onErrorResume {
                logger.error { it.localizedMessage }
                Mono.error {
                    ApplicationException(message = it.localizedMessage, cause = Throwable(it.localizedMessage))
                }
            }
    }
}