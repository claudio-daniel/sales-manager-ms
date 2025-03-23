package com.bk.reactive.app.commerce.admin.my.service

import com.bk.reactive.app.commerce.admin.my.dao.TurnDAO
import com.bk.reactive.app.commerce.admin.my.model.api.response.TurnResponse
import com.bk.reactive.app.commerce.admin.my.model.mapper.TurnMapper.Companion.fromTurnToTurnResponse
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class TurnService(
    private val turnDAO: TurnDAO
) {
    fun findAllTurnResponse(): Flux<TurnResponse> {
        return turnDAO.findAll()
            .map { fromTurnToTurnResponse(it) }
            .onErrorResume {
                Mono.error { Exception("There are no registered turns") }
            }
    }
}