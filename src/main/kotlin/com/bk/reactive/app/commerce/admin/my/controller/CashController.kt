package com.bk.reactive.app.commerce.admin.my.controller

import com.bk.reactive.app.commerce.admin.my.model.api.request.cash.CreateCashRequest
import com.bk.reactive.app.commerce.admin.my.model.api.request.cash.FindCashRequest
import com.bk.reactive.app.commerce.admin.my.model.api.request.cash.UpdateCashRequest
import com.bk.reactive.app.commerce.admin.my.model.api.response.CashResponse
import com.bk.reactive.app.commerce.admin.my.model.api.response.CashStatusResponse
import com.bk.reactive.app.commerce.admin.my.model.constant.HttpCodesConstant.Companion.STATUS_BAD_REQUEST
import com.bk.reactive.app.commerce.admin.my.model.constant.HttpCodesConstant.Companion.STATUS_FORBIDDEN
import com.bk.reactive.app.commerce.admin.my.model.constant.HttpCodesConstant.Companion.STATUS_OK
import com.bk.reactive.app.commerce.admin.my.service.CashService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import mu.KotlinLogging
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Api(tags = ["Cash Controller"], value = "Permite administrar las cajas")
@RestController
class CashController(
    private val cashService: CashService
) {
    private val logger = KotlinLogging.logger {}

    @ApiOperation(value = "Get cashes by filter", response = CashResponse::class)
    @ApiResponses(
        value = [
            ApiResponse(code = STATUS_OK, message = "Ok"),
            ApiResponse(code = STATUS_BAD_REQUEST, message = "Bad Request"),
            ApiResponse(code = STATUS_FORBIDDEN, message = "Full authentication is required to access this resource")
        ]
    )
    @GetMapping("cash")
    fun findCashes(
        findCashRequest: FindCashRequest
    ): Flux<CashResponse> {
        logger.info { "Sending cashes by filters" }
        return cashService.cashFilter(findCashRequest)
    }

    @ApiOperation(value = "Get cash detail by id", response = CashResponse::class)
    @ApiResponses(
        value = [
            ApiResponse(code = STATUS_OK, message = "Ok"),
            ApiResponse(code = STATUS_BAD_REQUEST, message = "Bad Request"),
            ApiResponse(code = STATUS_FORBIDDEN, message = "Full authentication is required to access this resource")
        ]
    )
    @GetMapping("cash/{id}")
    fun findById(
        @ApiParam(value = "cash id", example = "1111")
        @PathVariable(value = "id", required = false) id: String
    ): Mono<CashResponse> {
        logger.info { "Sending cash detail by id" }
        return cashService.findCashResponseById(id)
    }

    @ApiOperation(value = "Create cash", response = CashResponse::class)
    @ApiResponses(
        value = [
            ApiResponse(code = STATUS_OK, message = "Ok"),
            ApiResponse(code = STATUS_BAD_REQUEST, message = "Bad Request"),
            ApiResponse(code = STATUS_FORBIDDEN, message = "Full authentication is required to access this resource")
        ]
    )
    @PostMapping("cash")
    fun createCash(
        @ApiParam(value = "Cash to create", example = "1111")
        @RequestBody @Validated createCashRequest: CreateCashRequest
    ): Mono<CashResponse> {
        logger.info { "Saving new cash from request" }
        return cashService.createCash(createCashRequest)
    }

    @ApiOperation(value = "Update cash", response = CashResponse::class)
    @ApiResponses(
        value = [
            ApiResponse(code = STATUS_OK, message = "Ok"),
            ApiResponse(code = STATUS_BAD_REQUEST, message = "Bad Request"),
            ApiResponse(code = STATUS_FORBIDDEN, message = "Full authentication is required to access this resource")
        ]
    )
    @PutMapping("cash/{id}")
    fun updateCash(
        @ApiParam(value = "Cash to create", example = "1111")
        @PathVariable id: String,

        @ApiParam(value = "Cash to create", example = "1111")
        @RequestBody @Validated updateCashRequest: UpdateCashRequest
    ): Mono<CashResponse> {
        logger.info { "Updating cash from request" }
        return cashService.update(id, updateCashRequest)
    }

    @ApiOperation(value = "Delete cash", response = CashResponse::class)
    @ApiResponses(
        value = [
            ApiResponse(code = STATUS_OK, message = "Ok"),
            ApiResponse(code = STATUS_BAD_REQUEST, message = "Bad Request"),
            ApiResponse(code = STATUS_FORBIDDEN, message = "Full authentication is required to access this resource")
        ]
    )
    @DeleteMapping("cash/{id}")
    fun deleteCash(
        @ApiParam(value = "Cash to delete", example = "1111")
        @PathVariable("id") id: String
    ): Mono<CashResponse> {
        return cashService.deleteCash(id)
    }

    @ApiOperation(value = "All possible cash status", response = CashStatusResponse::class)
    @ApiResponses(
        value = [
            ApiResponse(code = STATUS_OK, message = "Ok"),
            ApiResponse(code = STATUS_BAD_REQUEST, message = "Bad Request"),
            ApiResponse(code = STATUS_FORBIDDEN, message = "Full authentication is required to access this resource")
        ]
    )
    @GetMapping("cash-status")
    fun findAllCashStatus(): Flux<CashStatusResponse> {
        logger.info { "Sending all cash status" }
        return cashService.findAllCashStatus()
    }
}