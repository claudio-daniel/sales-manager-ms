package com.bk.reactive.app.commerce.admin.my.service.validator.cash

import com.bk.reactive.app.commerce.admin.my.exception.api.CashException
import com.bk.reactive.app.commerce.admin.my.exception.code.ErrorCode
import com.bk.reactive.app.commerce.admin.my.model.constant.BusinessModelConstant.Companion.CASH_STATUS_INACTIVE_LONG
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CashStatusValidator {
    private val logger = KotlinLogging.logger {}

    fun validateCashStatusRequest(statusId: String): Mono<String> {
        return Mono.just(statusId)
            .map {
                if (statusId == CASH_STATUS_INACTIVE_LONG.toString()) {
                    val message = ErrorCode.INVALID_CASH_STATUS.description

                    logger.error { message }
                    Mono.error<String> {
                        CashException(
                            message = message,
                            code = HttpStatus.CONFLICT,
                            cause = Throwable(message)
                        )
                    }
                }
                it
            }
    }
}