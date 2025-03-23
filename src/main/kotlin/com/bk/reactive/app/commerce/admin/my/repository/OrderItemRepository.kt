package com.bk.reactive.app.commerce.admin.my.repository

import com.bk.reactive.app.commerce.admin.my.model.document.OrderItem
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderItemRepository : ReactiveMongoRepository<OrderItem, String> {
}