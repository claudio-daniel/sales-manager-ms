package com.bk.reactive.app.commerce.admin.my.service

import com.bk.reactive.app.commerce.admin.my.dao.OrderDAO
import com.bk.reactive.app.commerce.admin.my.dao.ProductDAO
import com.bk.reactive.app.commerce.admin.my.model.api.request.order.CreateOrderItemsRequest
import com.bk.reactive.app.commerce.admin.my.model.api.request.order.CreateOrderRequest
import com.bk.reactive.app.commerce.admin.my.model.document.Order
import com.bk.reactive.app.commerce.admin.my.model.document.OrderItem
import com.bk.reactive.app.commerce.admin.my.model.document.OrderType
import com.bk.reactive.app.commerce.admin.my.model.document.Product
import mu.KotlinLogging
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class OrderService(
    private val orderDAO: OrderDAO,
    private val productDAO: ProductDAO,
    private val exceptionService: ExceptionService
) {
    private val logger = KotlinLogging.logger {}

    fun createOrderItems(createOrderRequest: CreateOrderRequest): Flux<OrderItem> {
        return Flux
            .fromIterable(createOrderRequest.createOrderItemsRequest)
            .flatMap { orderItem -> createOrderItem(orderItem) }
    }

    private fun createOrderItem(createOrderItemsRequest: CreateOrderItemsRequest): Mono<OrderItem> {
        return findProductById(createOrderItemsRequest.productId)
            .map { product ->
                OrderItem(
                    id = null,
                    orderId = null,
                    product = product,
                    count = createOrderItemsRequest.count
                )
            }
    }

    fun findOrderTypeById(orderTypeId: String): Mono<OrderType> {
        return orderDAO.findOrderTypeById(orderTypeId)
            .switchIfEmpty(
                Mono.error {
                    exceptionService.throwNotFoundException("No se encontraron tipos de pedidos.")
                }
            )
    }

    fun findAll(): Flux<Order> = orderDAO.findAll()

    fun findAllOrderTypes(): Flux<OrderType> = orderDAO.findAllOrderTypes()

    private fun findProductById(productId: String): Mono<Product> = productDAO.findById(productId)
}
