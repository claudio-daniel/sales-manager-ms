package com.bk.reactive.app.commerce.admin.my.service

import com.bk.reactive.app.commerce.admin.my.dao.SaleDAO
import com.bk.reactive.app.commerce.admin.my.exception.api.FilterSaleException
import com.bk.reactive.app.commerce.admin.my.exception.api.NotFoundSaleException
import com.bk.reactive.app.commerce.admin.my.exception.api.SaleException
import com.bk.reactive.app.commerce.admin.my.exception.api.SaveSaleException
import com.bk.reactive.app.commerce.admin.my.model.api.request.client.CreateClientRequest
import com.bk.reactive.app.commerce.admin.my.model.api.request.order.CreateOrderRequest
import com.bk.reactive.app.commerce.admin.my.model.api.request.sale.CreateSaleRequest
import com.bk.reactive.app.commerce.admin.my.model.api.response.SaleResponse
import com.bk.reactive.app.commerce.admin.my.model.document.Cash
import com.bk.reactive.app.commerce.admin.my.model.document.Client
import com.bk.reactive.app.commerce.admin.my.model.document.Order
import com.bk.reactive.app.commerce.admin.my.model.document.OrderItem
import com.bk.reactive.app.commerce.admin.my.model.document.OrderType
import com.bk.reactive.app.commerce.admin.my.model.document.PaymentType
import com.bk.reactive.app.commerce.admin.my.model.document.Sale
import com.bk.reactive.app.commerce.admin.my.model.mapper.SaleMapper
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.util.function.component1
import reactor.kotlin.core.util.function.component2
import reactor.kotlin.core.util.function.component3
import reactor.kotlin.core.util.function.component4
import java.time.ZonedDateTime

@Service
class SaleService(
    private val saleDAO: SaleDAO,

    private val cashService: CashService,
    private val clientService: ClientService,
    private val orderService: OrderService,
    private val paymentTypeService: PaymentTypeService,
) {
    private val logger = KotlinLogging.logger { }

    fun salesFilter(
        dateFrom: ZonedDateTime, dateTo: ZonedDateTime, userId: String?,
        clientDni: String?, productTypeId: String?, productId: String?,
        paymentTypeId: String?
    ): Flux<SaleResponse> {
        return saleDAO.salesFilter(dateFrom, dateTo, userId, clientDni, productId, productTypeId, paymentTypeId)
            .switchIfNotFound()
            .map { SaleMapper.fromSaleToSaleResponse(it) }
            .onErrorSaleFilterResume()
    }

    fun saveSale(createSaleRequest: CreateSaleRequest): Mono<SaleResponse> {
        return Mono.zipDelayError(
            createAndSumCash(createSaleRequest.cashId, createSaleRequest.total),
            createClient(createSaleRequest.createClientRequest),
            createOrder(createSaleRequest.createOrderRequest),
            createPaymentType(createSaleRequest.paymentTypeId)
        ).flatMap { (cash, client, order, paymentType) ->
            saveSale(
                Sale(
                    id = null,
                    cash = cash,
                    order = order,
                    client = client,
                    date = getNowZonedDateTime(),
                    paymentType = paymentType,
                    payment = createSaleRequest.payment,
                    returned = createSaleRequest.returned,
                    total = createSaleRequest.total
                )
            )
        }.map {
            SaleMapper.fromSaleToSaleResponse(it)
        }.onErrorSaveSaleResume()
    }

    fun saveSale(sale: Sale): Mono<Sale> {
        return saleDAO.save(sale)
            .doOnError {
                logger.error { "An error occurred while save a sale : $sale" }
            }
    }

    fun createOrder(createOrderRequest: CreateOrderRequest): Mono<Order> {
        return Mono.zip(
            findOrderTypeById(createOrderRequest.orderTypeId),
            createOrderItems(createOrderRequest).collectList()
        ).map { (orderType, orderItems) ->
            Order(
                id = null,
                orderItems = orderItems,
                orderType = orderType
            )
        }
    }

    private fun createAndSumCash(cashId: String, totalSale: Double): Mono<Cash> {
        return cashService.findById(cashId)
            .flatMap { cash ->
                cash.partialBalance += totalSale
                cashService.updatePartialBalance(cash.id!!, cash)
            }
    }

    private fun createClient(createClientRequest: CreateClientRequest): Mono<Client> =
        clientService.findByDniOrElseSave(createClientRequest)

    private fun createOrderItems(createOrderRequest: CreateOrderRequest): Flux<OrderItem> =
        orderService.createOrderItems(createOrderRequest)

    private fun createPaymentType(paymentTypeId: String): Mono<PaymentType> = paymentTypeService.findById(paymentTypeId)

    private fun findOrderTypeById(orderTypeId: String): Mono<OrderType> = orderService.findOrderTypeById(orderTypeId)

    private fun getNowZonedDateTime(): ZonedDateTime = ZonedDateTime.now()

    private fun Mono<SaleResponse>.onErrorSaveSaleResume(): Mono<SaleResponse> =
        this.onErrorResume {
            logger.error { it.printStackTrace() }
            Mono.error {
                SaveSaleException(
                    code = HttpStatus.CONFLICT,
                    message = it.localizedMessage,
                    suppressedSaleException = it.suppressed
                )
            }
        }

    private fun Flux<Sale>.switchIfNotFound(): Flux<Sale> =
        this.switchIfEmpty(
            Flux.error {
                NotFoundSaleException(
                    message = "No sale for selected filters.",
                    code = HttpStatus.NOT_FOUND
                )
            }
        )

    private fun Flux<SaleResponse>.onErrorSaleFilterResume(): Flux<SaleResponse> =
        this.onErrorResume {
            logger.error { it.printStackTrace() }
            if (it is SaleException) {
                Mono.error { it }
            } else {
                Mono.error {
                    FilterSaleException(
                        message = it.localizedMessage,
                        code = HttpStatus.INTERNAL_SERVER_ERROR
                    )
                }
            }
        }
}