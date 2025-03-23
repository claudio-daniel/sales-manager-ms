package com.bk.reactive.app.commerce.admin.my.dao

import com.bk.reactive.app.commerce.admin.my.model.document.Product
import com.bk.reactive.app.commerce.admin.my.model.document.ProductType
import com.bk.reactive.app.commerce.admin.my.repository.ProductRepository
import com.bk.reactive.app.commerce.admin.my.repository.ProductTypeRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class ProductDAO(
    private val productRepository: ProductRepository,
    private val productTypeRepository: ProductTypeRepository
) {
    fun productFilter(id: String?, productTypeId: String?, nameCode: String?): Flux<Product> {
        return productRepository.productFilter(id, productTypeId)
    }

    fun findProductToppingsByMainDishId(mainDishId: String): Flux<Product> {
        return findById(mainDishId)
            .map { product ->
                product.toppings
            }.flatMapMany {
                Flux.fromIterable(it)
            }.flatMap { topping ->
                findById(topping)
            }
    }

    fun findById(id: String): Mono<Product> = productRepository.findById(id)

    fun findAll(): Flux<Product> = productRepository.findAll()

    fun findAllProductsTypes(): Flux<ProductType> = productTypeRepository.findAll()
}