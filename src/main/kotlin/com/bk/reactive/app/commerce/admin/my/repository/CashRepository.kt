package com.bk.reactive.app.commerce.admin.my.repository

import com.bk.reactive.app.commerce.admin.my.model.document.Cash
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface CashRepository : ReactiveMongoRepository<Cash, String> {
    @Query("{'cashStatus._id' : ObjectId(?0)}")
    fun findByCashStatusId(cashStatusId: String): Flux<Cash>

    fun findByStartDateBetween(dateFrom: String?, dateTo: String?): Flux<Cash>
}