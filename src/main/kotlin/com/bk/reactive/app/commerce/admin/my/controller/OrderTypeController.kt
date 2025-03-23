package com.bk.reactive.app.commerce.admin.my.controller

import com.bk.reactive.app.commerce.admin.my.model.constant.HttpCodesConstant.Companion.STATUS_BAD_REQUEST
import com.bk.reactive.app.commerce.admin.my.model.constant.HttpCodesConstant.Companion.STATUS_FORBIDDEN
import com.bk.reactive.app.commerce.admin.my.model.constant.HttpCodesConstant.Companion.STATUS_OK
import com.bk.reactive.app.commerce.admin.my.model.document.OrderType
import com.bk.reactive.app.commerce.admin.my.service.OrderService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@Api(tags = ["Order Type Controller"])
@RestController
class OrderTypeController(
    private val orderService: OrderService
) {
    private val logger = KotlinLogging.logger {}

    @ApiOperation(value = "Get Order Types", response = OrderType::class)
    @ApiResponses(
        value = [
            ApiResponse(code = STATUS_OK, message = "Ok"),
            ApiResponse(code = STATUS_BAD_REQUEST, message = "Bad Request"),
            ApiResponse(code = STATUS_FORBIDDEN, message = "Full authentication is required to access this resource")
        ]
    )
    @GetMapping("order-type")
    fun getPaymentTypes(): Flux<OrderType> {
        logger.info { "Searching all order types" }

        return orderService.findAllOrderTypes()
    }
}