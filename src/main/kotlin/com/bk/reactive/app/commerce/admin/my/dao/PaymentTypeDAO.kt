package com.bk.reactive.app.commerce.admin.my.dao

import com.bk.reactive.app.commerce.admin.my.model.document.PaymentType
import com.bk.reactive.app.commerce.admin.my.repository.PaymentTypeRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class PaymentTypeDAO(
    private val paymentTypeRepository: PaymentTypeRepository
) {
    fun findById(id: String): Mono<PaymentType> = paymentTypeRepository.findById(id)

    fun findAll(): Flux<PaymentType> = paymentTypeRepository.findAll()
}