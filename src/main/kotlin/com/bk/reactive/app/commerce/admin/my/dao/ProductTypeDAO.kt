package com.bk.reactive.app.commerce.admin.my.dao

import com.bk.reactive.app.commerce.admin.my.model.document.ProductType
import com.bk.reactive.app.commerce.admin.my.repository.ProductTypeRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
class ProductTypeDAO(
    private val productTypeRepository: ProductTypeRepository
) {
    fun getAll(): Flux<ProductType> = productTypeRepository.findAll()
}