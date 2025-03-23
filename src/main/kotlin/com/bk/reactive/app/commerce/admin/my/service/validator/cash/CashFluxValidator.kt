package com.bk.reactive.app.commerce.admin.my.service.validator.cash

import com.bk.reactive.app.commerce.admin.my.model.api.request.cash.CreateCashRequest
import com.bk.reactive.app.commerce.admin.my.model.api.request.cash.UpdateCashRequest
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CashFluxValidator(
    private val cashStatusValidator: CashStatusValidator
) {
    fun validateRequestForCreate(createCashRequest: CreateCashRequest): Mono<String> {
        return cashStatusValidator.validateCashStatusRequest(createCashRequest.statusId)
    }

    fun validateRequestForUpdate(updateCashRequest: UpdateCashRequest): Mono<String> {
        return cashStatusValidator.validateCashStatusRequest(updateCashRequest.statusId)
    }
}