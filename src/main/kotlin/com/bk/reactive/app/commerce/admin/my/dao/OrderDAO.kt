package com.bk.reactive.app.commerce.admin.my.dao

import com.bk.reactive.app.commerce.admin.my.model.document.Order
import com.bk.reactive.app.commerce.admin.my.model.document.OrderItem
import com.bk.reactive.app.commerce.admin.my.model.document.OrderType
import com.bk.reactive.app.commerce.admin.my.repository.OrderItemRepository
import com.bk.reactive.app.commerce.admin.my.repository.OrderRepository
import com.bk.reactive.app.commerce.admin.my.repository.OrderTypeRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class OrderDAO(
    private val orderRepository: OrderRepository,
    private val orderTypeRepository: OrderTypeRepository,
    private val orderItemRepository: OrderItemRepository
) {
    fun findAll(): Flux<Order> = orderRepository.findAll()

    fun save(order: Order): Mono<Order> = orderRepository.save(order)

    fun findOrderTypeById(orderTypeId: String): Mono<OrderType> = orderTypeRepository.findById(orderTypeId)

    fun findAllOrderTypes(): Flux<OrderType> = orderTypeRepository.findAll()

    fun saveOrderItem(orderItem: OrderItem) = orderItemRepository.save(orderItem)
}