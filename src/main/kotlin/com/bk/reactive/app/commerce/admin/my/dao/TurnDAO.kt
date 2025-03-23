package com.bk.reactive.app.commerce.admin.my.dao

import com.bk.reactive.app.commerce.admin.my.model.document.Turn
import com.bk.reactive.app.commerce.admin.my.repository.TurnRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class TurnDAO(
    private val turnRepository: TurnRepository
) {
    fun findAll(): Flux<Turn> {
        return turnRepository.findAll()
    }

    fun findById(id: String): Mono<Turn> {
        return turnRepository.findById(id)
    }
}