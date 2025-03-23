package com.bk.reactive.app.commerce.admin.my.service

import com.bk.reactive.app.commerce.admin.my.dao.PaymentTypeDAO
import com.bk.reactive.app.commerce.admin.my.exception.api.ApplicationException
import com.bk.reactive.app.commerce.admin.my.model.document.PaymentType
import mu.KotlinLogging
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PaymentTypeService(
    private val paymentTypeDAO: PaymentTypeDAO
) {
    private val logger = KotlinLogging.logger { }

    fun findById(id: String): Mono<PaymentType> {
        return paymentTypeDAO.findById(id)
            .onErrorResume {
                logger.error { it.localizedMessage }
                Mono.error {
                    ApplicationException(message = it.localizedMessage, cause = Throwable(it.localizedMessage))
                }
            }
    }

    fun findAll(): Flux<PaymentType> {
        return paymentTypeDAO.findAll()
            .onErrorResume {
                logger.error { it.localizedMessage }
                Mono.error {
                    ApplicationException(message = it.localizedMessage, cause = Throwable(it.localizedMessage))
                }
            }
    }
}