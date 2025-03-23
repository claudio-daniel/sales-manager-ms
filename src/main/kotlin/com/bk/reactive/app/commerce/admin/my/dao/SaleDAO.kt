package com.bk.reactive.app.commerce.admin.my.dao

import com.bk.reactive.app.commerce.admin.my.exception.api.FilterSaleException
import com.bk.reactive.app.commerce.admin.my.exception.api.SaveSaleException
import com.bk.reactive.app.commerce.admin.my.model.document.Order
import com.bk.reactive.app.commerce.admin.my.model.document.OrderItem
import com.bk.reactive.app.commerce.admin.my.model.document.Sale
import com.bk.reactive.app.commerce.admin.my.repository.SaleRepository
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.util.function.component1
import reactor.kotlin.core.util.function.component2
import java.time.ZonedDateTime

@Component
class SaleDAO(
    private val saleRepository: SaleRepository,
    private val orderDAO: OrderDAO
) {
    private val logger = KotlinLogging.logger {}

    fun salesFilter(
        dateFrom: ZonedDateTime, dateTo: ZonedDateTime,
        userId: String?, clientDni: String?, productTypeId: String?,
        productId: String?, paymentTypeId: String?
    ): Flux<Sale> {
        return saleRepository.findByDateBetween(dateFrom, dateTo)
            .filter { userId == null || it.cash.user.id == userId }
            .filter { clientDni == null || it.client.dni == clientDni }
            .filter { paymentTypeId == null || it.paymentType.id == paymentTypeId }
            .filter {
                productId == null ||
                        it.order.orderItems.any { orderItem -> orderItem.product.id == productId }
            }
            .filter {
                productTypeId == null ||
                        it.order.orderItems.any { orderItem -> orderItem.product.productType.id == productTypeId }
            }.onErrorResume {
                logger.error { "An error occurred while filter sales" }
                logger.error { it.printStackTrace() }

                Mono.error {
                    FilterSaleException(
                        message = "Can not find sales: ${it.localizedMessage}",
                        code = HttpStatus.INTERNAL_SERVER_ERROR
                    )
                }
            }
    }

    fun save(sale: Sale): Mono<Sale> {
        return saveOrder(sale.order)
            .flatMap { orderSaved ->
                Mono.zip(
                    Mono.just(saveOrderItems(orderSaved).subscribe()),
                    Mono.just(orderSaved)
                )
            }.flatMap { (_, order) ->
                saleRepository.save(
                    Sale(
                        id = null,
                        order = order,
                        client = sale.client,
                        date = sale.date,
                        paymentType = sale.paymentType,
                        cash = sale.cash,
                        payment = sale.payment,
                        returned = sale.returned,
                        total = sale.total
                    )
                )
            }.doOnNext {
                logger.info { "sale saved" }
            }.onErrorResume {
                logger.error { "An error occurred while save sale" }
                logger.error { it.printStackTrace() }

                Mono.error {
                    SaveSaleException(
                        code = HttpStatus.CONFLICT,
                        message = it.localizedMessage,
                        suppressedSaleException = it.suppressed
                    )
                }
            }
    }

    private fun saveOrder(order: Order): Mono<Order> {
        return orderDAO.save(order)
            .doOnNext { logger.info { "order saved" } }
            .doOnError { logger.error { it } }
    }

    private fun saveOrderItems(order: Order): Flux<OrderItem> {
        return Flux.fromIterable(order.orderItems)
            .map {
                OrderItem(
                    id = null,
                    orderId = order.id!!,
                    product = it.product,
                    count = it.count
                )
            }.flatMap { orderItem ->
                orderDAO.saveOrderItem(orderItem)
            }.doOnNext {
                logger.info { "order item saved" }
            }.doOnError { logger.error { it } }
    }
}