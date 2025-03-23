package com.bk.reactive.app.commerce.admin.my.service

import com.bk.reactive.app.commerce.admin.my.dao.CashDAO
import com.bk.reactive.app.commerce.admin.my.dao.TurnDAO
import com.bk.reactive.app.commerce.admin.my.dao.UserDAO
import com.bk.reactive.app.commerce.admin.my.exception.api.SaveCashException
import com.bk.reactive.app.commerce.admin.my.exception.api.SaveSaleException
import com.bk.reactive.app.commerce.admin.my.helper.DateHelper
import com.bk.reactive.app.commerce.admin.my.model.api.request.cash.CreateCashRequest
import com.bk.reactive.app.commerce.admin.my.model.api.request.cash.FindCashRequest
import com.bk.reactive.app.commerce.admin.my.model.api.request.cash.UpdateCashRequest
import com.bk.reactive.app.commerce.admin.my.model.api.response.CashResponse
import com.bk.reactive.app.commerce.admin.my.model.api.response.CashStatusResponse
import com.bk.reactive.app.commerce.admin.my.model.api.response.SaleResponse
import com.bk.reactive.app.commerce.admin.my.model.document.Cash
import com.bk.reactive.app.commerce.admin.my.model.document.CashStatus
import com.bk.reactive.app.commerce.admin.my.model.document.Turn
import com.bk.reactive.app.commerce.admin.my.model.document.User
import com.bk.reactive.app.commerce.admin.my.model.mapper.CashMapper
import com.bk.reactive.app.commerce.admin.my.service.validator.cash.CashFluxValidator
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.util.function.component1
import reactor.kotlin.core.util.function.component2
import reactor.kotlin.core.util.function.component3
import java.time.ZonedDateTime

@Service
class CashService(
    private val cashDao: CashDAO,
    private val userDao: UserDAO,
    private val turnDao: TurnDAO,
    private val dateHelper: DateHelper,
    private val cashFluxValidator: CashFluxValidator,
    private val exceptionService: ExceptionService
) {
    private val logger = KotlinLogging.logger { }

    fun createCash(createCashRequest: CreateCashRequest): Mono<CashResponse> {
        return cashFluxValidator.validateRequestForCreate(createCashRequest)
            .flatMap {
                Mono.zipDelayError(
                    findUserById(createCashRequest.userId),
                    findTurnById(createCashRequest.turnId),
                    findCashStatusById(createCashRequest.statusId)
                )
            }.flatMap { (user, turn, cashStatus) ->
                save(
                    Cash(
                        user = user,
                        turn = turn,
                        startDate = getNowZonedDateTime(),
                        startMoney = createCashRequest.startMoney,
                        cashStatus = cashStatus
                    )
                )
            }.map { cash ->
                CashMapper.fromCashToCashResponse(cash)
            }.doOnNext { cashSaved ->
                logger.info { "Cash with id ${cashSaved.id} saved" }
            }.onErrorSaveCashResume()
    }

    fun update(id: String, updateCashRequest: UpdateCashRequest): Mono<CashResponse> {
        return cashFluxValidator.validateRequestForUpdate(updateCashRequest)
            .flatMap { findById(id) }
            .flatMap { cashToUpdate ->
                if (updateCashRequest.statusId == cashToUpdate.cashStatus.id)
                    Mono.zip(
                        Mono.just(cashToUpdate),
                        Mono.just(cashToUpdate.cashStatus)
                    )
                else Mono.zip(
                    Mono.just(cashToUpdate),
                    findCashStatusById(updateCashRequest.statusId)
                )
            }.flatMap { (cashToUpdate, cashStatus) ->
                save(
                    Cash(
                        id = cashToUpdate.id,
                        turn = cashToUpdate.turn,
                        user = cashToUpdate.user,
                        cashStatus = cashStatus,
                        startMoney = updateCashRequest.startMoney,
                        startDate = updateCashRequest.startDate,
                        partialBalance = cashToUpdate.partialBalance
                    )
                )
            }.map { savedCash ->
                CashMapper.fromCashToCashResponse(savedCash)
            }.onErrorResume {
                val error = "Error while updating cash whit id $id"
                logger.error { error }
                Mono.error { exceptionService.throwConflictException(it.localizedMessage, error) }
            }
    }

    fun updatePartialBalance(id: String, cash: Cash): Mono<Cash> {
        return findById(id)
            .flatMap {
                save(
                    Cash(
                        id = cash.id,
                        turn = cash.turn,
                        user = cash.user,
                        cashStatus = cash.cashStatus,
                        startMoney = cash.startMoney,
                        startDate = cash.startDate,
                        partialBalance = cash.partialBalance
                    )
                )
            }
    }

    fun cashFilter(
        findCashRequest: FindCashRequest
    ): Flux<CashResponse> {
        return cashDao
            .findAll()
            .filterCashes(findCashRequest)
            .map { CashMapper.fromCashToCashResponse(it) }
            .onErrorResume {
                val error = "Error while sending cashes by filters"
                logger.error { error }
                Mono.error { exceptionService.throwNotFoundException(it.localizedMessage, error) }
            }
    }

    fun findCashResponseById(id: String): Mono<CashResponse> {
        return findById(id)
            .map { CashMapper.fromCashToCashResponse(it) }
    }

    fun deleteCash(id: String): Mono<CashResponse> {
        return findById(id)
            .flatMap { cash ->
                cashDao.delete(cash).flatMap { Mono.just(cash) }
            }.map { cashDeleted ->
                CashMapper.fromCashToCashResponse(cashDeleted)
            }.onErrorResume { error ->
                val message = "Error while deleting cash with id ${id}."
                logger.error { message }
                Mono.error { exceptionService.throwConflictException(error.localizedMessage, message) }
            }.doOnNext {
                logger.info { "Cash with id $id deleted" }
            }
    }

    fun findById(id: String): Mono<Cash> {
        return cashDao.findById(id)
            .switchIfEmpty(
                Mono.error { exceptionService.throwNotFoundException(id, "cashId") })
            .onErrorResume {
                logger.error { it.localizedMessage }
                Mono.error { it }
            }
    }

    fun findAllCashStatus(): Flux<CashStatusResponse> {
        return cashDao.findAllCashStatus()
            .map { cashStatus -> CashMapper.fromCashStatusToCashStatusResponse(cashStatus) }
    }

    private fun save(cash: Cash): Mono<Cash> {
        return cashDao.save(cash)
            .doOnError {
                logger.error { "An error occurred while save a cash : $cash" }
            }
    }

    //ATBBfMqtNUQEcKeaLSSPnhBxWB3kF3EE0B53 bitbuket token
    private fun findCashStatusById(cashStatusId: String): Mono<CashStatus> {
        return cashDao.findCashStatusById(cashStatusId)
            .switchIfEmpty(
                Mono.error { exceptionService.throwNotFoundException(cashStatusId, "cashStatusId") })
            .onErrorResume {
                logger.error { it.localizedMessage }
                Mono.error { it }
            }
    }

    private fun findUserById(userId: String): Mono<User> {
        return userDao.findById(userId)
            .switchIfEmpty(
                Mono.error { exceptionService.throwNotFoundException(userId, "userId") })
            .onErrorResume {
                logger.error { it.localizedMessage }
                Mono.error { it }
            }
    }

    private fun findTurnById(turnId: String): Mono<Turn> {
        return turnDao.findById(turnId)
            .switchIfEmpty(
                Mono.error { exceptionService.throwNotFoundException(turnId, "turnId") })
            .onErrorResume {
                logger.error { it.localizedMessage }
                Mono.error { it }
            }
    }

    private fun Flux<Cash>.filterCashes(
        findCashRequest: FindCashRequest
    ) = this.filter { cash -> findCashRequest.id == null || cash.id == findCashRequest.id }
        .filter { cash -> findCashRequest.cashStatusId == null || cash.cashStatus.id == findCashRequest.cashStatusId }
        .filter { cash -> findCashRequest.turnId == null || cash.turn.id == findCashRequest.turnId }
        .filter { cash -> findCashRequest.userId == null || cash.user.id == findCashRequest.userId }
        .filter { cash -> findCashRequest.dateFrom == null || cash.startDate >= ZonedDateTime.parse(findCashRequest.dateFrom) }
        .filter { cash -> findCashRequest.dateTo == null || cash.startDate <= ZonedDateTime.parse(findCashRequest.dateTo) }

    private fun Mono<CashResponse>.onErrorSaveCashResume(): Mono<CashResponse> =
        this.onErrorResume {
            logger.error { it.printStackTrace() }
            Mono.error {
                SaveCashException(
                    code = HttpStatus.CONFLICT,
                    message = it.localizedMessage,
                    suppressedSaleException = it.suppressed
                )
            }
        }

    private fun getNowZonedDateTime(): ZonedDateTime = dateHelper.getNowZonedDateTime()
}