package com.bk.reactive.app.commerce.admin.my.controller

import com.bk.reactive.app.commerce.admin.my.model.api.request.sale.CreateSaleRequest
import com.bk.reactive.app.commerce.admin.my.model.api.response.SaleResponse
import com.bk.reactive.app.commerce.admin.my.model.constant.HttpCodesConstant.Companion.STATUS_BAD_REQUEST
import com.bk.reactive.app.commerce.admin.my.model.constant.HttpCodesConstant.Companion.STATUS_FORBIDDEN
import com.bk.reactive.app.commerce.admin.my.model.constant.HttpCodesConstant.Companion.STATUS_OK
import com.bk.reactive.app.commerce.admin.my.service.SaleService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import mu.KotlinLogging
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.ZonedDateTime

@Api(tags = ["Sale Controller"], value = "Manage sale flux")
@RestController
class SaleController(
    private val saleService: SaleService
) {
    private val logger = KotlinLogging.logger {}

    @ApiOperation(value = "Create Sale", response = SaleResponse::class)
    @ApiResponses(
        value = [
            ApiResponse(code = STATUS_OK, message = "Ok"),
            ApiResponse(code = STATUS_BAD_REQUEST, message = "Bad Request"),
            ApiResponse(code = STATUS_FORBIDDEN, message = "Full authentication is required to access this resource")]
    )
    @PostMapping("sale")
    fun createSale(
        @ApiParam(value = "Sale to save", required = true)
        @RequestBody @Validated createSaleRequest: CreateSaleRequest
    ): Mono<SaleResponse> {
        logger.info { "Create sale records" }
        return saleService.saveSale(createSaleRequest)
    }

    @ApiOperation(value = "Get filtered sales", response = SaleResponse::class)
    @ApiResponses(
        value = [
            ApiResponse(code = STATUS_OK, message = "Ok"),
            ApiResponse(code = STATUS_BAD_REQUEST, message = "Bad Request"),
            ApiResponse(code = STATUS_FORBIDDEN, message = "Full authentication is required to access this resource")]
    )
    @GetMapping("sale")
    fun getFilteredSales(
        @ApiParam(value = "date from", example = "2021-12-01T19:40:47-03:00")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @RequestParam(value = "dateFrom") dateFrom: ZonedDateTime?,

        @ApiParam(value = "date to", example = "2021-12-01T19:40:47-03:00")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @RequestParam(value = "dateTo") dateTo: ZonedDateTime?,

        @ApiParam(value = "user Id", example = "111")
        @RequestParam(value = "userId", required = false) userId: String?,

        @ApiParam(value = "client Dni", example = "111")
        @RequestParam(value = "clientDni", required = false) clientDni: String?,

        @ApiParam(value = "product type id", example = "111")
        @RequestParam(value = "productTypeId", required = false) productTypeId: String?,

        @ApiParam(value = "product id", example = "111")
        @RequestParam(value = "productId", required = false) productId: String?,

        @ApiParam(value = "payment type Id", example = "111")
        @RequestParam(value = "paymentTypeId", required = false) paymentTypeId: String?
    ): Flux<SaleResponse> {
        logger.info { "Searching sales by filters" }

        return saleService.salesFilter(dateFrom!!, dateTo!!, userId, clientDni, productId, productTypeId, paymentTypeId)
    }
}