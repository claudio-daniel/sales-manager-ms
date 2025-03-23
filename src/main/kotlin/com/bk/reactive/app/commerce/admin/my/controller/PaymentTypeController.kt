package com.bk.reactive.app.commerce.admin.my.controller

import com.bk.reactive.app.commerce.admin.my.model.constant.HttpCodesConstant.Companion.STATUS_BAD_REQUEST
import com.bk.reactive.app.commerce.admin.my.model.constant.HttpCodesConstant.Companion.STATUS_FORBIDDEN
import com.bk.reactive.app.commerce.admin.my.model.constant.HttpCodesConstant.Companion.STATUS_OK
import com.bk.reactive.app.commerce.admin.my.model.document.PaymentType
import com.bk.reactive.app.commerce.admin.my.service.PaymentTypeService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@Api(tags = ["Payment Type Controller"])
@RestController
class PaymentTypeController(
    private val paymentTypeService: PaymentTypeService
) {

    @ApiOperation(value = "Get Payment Types", response = PaymentType::class)
    @ApiResponses(
        value = [
            ApiResponse(code = STATUS_OK, message = "Ok"),
            ApiResponse(code = STATUS_BAD_REQUEST, message = "Bad Request"),
            ApiResponse(code = STATUS_FORBIDDEN, message = "Full authentication is required to access this resource")
        ]
    )
    @GetMapping("payment-type")
    fun getPaymentTypes(): Flux<PaymentType> {
        return paymentTypeService.findAll()
    }
}