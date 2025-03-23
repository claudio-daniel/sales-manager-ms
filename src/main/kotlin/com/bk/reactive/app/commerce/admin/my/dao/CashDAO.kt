package com.bk.reactive.app.commerce.admin.my.dao

import com.bk.reactive.app.commerce.admin.my.exception.api.SaveSaleException
import com.bk.reactive.app.commerce.admin.my.model.document.Cash
import com.bk.reactive.app.commerce.admin.my.model.document.CashStatus
import com.bk.reactive.app.commerce.admin.my.repository.CashRepository
import com.bk.reactive.app.commerce.admin.my.repository.CashStatusRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class CashDAO(
    private val cashRepository: CashRepository,
    private val cashStatusRepository: CashStatusRepository
) {
    fun findAll(): Flux<Cash> = cashRepository.findAll()

    fun findById(id: String): Mono<Cash> = cashRepository.findById(id)

    fun findByStatus(cashStatusId: String): Flux<Cash> = cashRepository.findByCashStatusId(cashStatusId)

    fun save(cash: Cash): Mono<Cash> = cashRepository.save(cash).onErrorResume {

        Mono.error {
            SaveSaleException(
                code = HttpStatus.CONFLICT,
                message = it.localizedMessage,
                suppressedSaleException = it.suppressed
            )
        }
    }

    fun delete(cash: Cash) = cashRepository.delete(cash)

    fun findCashStatusById(cashStatusId: String): Mono<CashStatus> = cashStatusRepository.findById(cashStatusId)

    fun findAllCashStatus(): Flux<CashStatus> = cashStatusRepository.findAll()
}