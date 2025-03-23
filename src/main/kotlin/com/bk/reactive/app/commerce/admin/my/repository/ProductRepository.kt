package com.bk.reactive.app.commerce.admin.my.repository

import com.bk.reactive.app.commerce.admin.my.model.document.Product
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface ProductRepository : ReactiveMongoRepository<Product, String> {
    @Query("{'productType._id' : :#{#productTypeId}}")
    fun findByProductTypeId(productTypeId: String): Flux<Product>

    //use SpEL syntax
    @Query(
        "{ \$and : ["
                + " {\$or : [ { \$where: \"':#{#productId}' == 'null'\" } , {_id: :#{#productId}} ]},"
                + " {\$or : [ { \$where: \"':#{#productTypeId}' == 'null'\" } , {'productType._id': :#{#productTypeId}} ]}"
                + " ]}"
    ) //+ "and (:nameCode is null or (product.name like %:nameCode% or product.code like %:nameCode%)) ")
    fun productFilter(productId: String?, productTypeId: String?): Flux<Product>
}