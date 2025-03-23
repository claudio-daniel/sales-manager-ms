package com.bk.reactive.app.commerce.admin.my.repository

import com.bk.reactive.app.commerce.admin.my.model.document.Sale
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.time.ZonedDateTime

@Repository
interface SaleRepository : ReactiveMongoRepository<Sale, String> {
    fun findByDateBetween(dateFrom: ZonedDateTime, dateTo: ZonedDateTime): Flux<Sale>
}