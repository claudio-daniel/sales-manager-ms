package com.bk.reactive.app.commerce.admin.my.service

import com.bk.reactive.app.commerce.admin.my.dao.ProductDAO
import com.bk.reactive.app.commerce.admin.my.exception.api.ApplicationException
import com.bk.reactive.app.commerce.admin.my.model.api.response.ProductResponse
import com.bk.reactive.app.commerce.admin.my.model.api.response.ProductTypeResponse
import com.bk.reactive.app.commerce.admin.my.model.mapper.ProductMapper.Companion.fromProductToProductResponse
import com.bk.reactive.app.commerce.admin.my.model.mapper.ProductTypeMapper.Companion.fromProductTypeToProductTypeResponse
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ProductService(
    private val productDAO: ProductDAO,
    private val exceptionService: ExceptionService
) {
    private val logger = KotlinLogging.logger {}

    fun findById(id: String): Mono<ProductResponse> {
        return productDAO.findById(id)
            .map { fromProductToProductResponse(it) }
            .onErrorResume {
                logger.error { it.localizedMessage }
                Mono.error {
                    ApplicationException(message = it.localizedMessage, cause = Throwable(it.localizedMessage))
                }
            }
    }

    fun productFilter(id: String?, productTypeId: String?, nameCode: String?): Flux<ProductResponse> {
        return productDAO.findAll()
            .filter { product -> id == null || product.id == id }
            .filter { product -> productTypeId == null || product.productType.id == productTypeId }
            .switchIfEmpty(
                Mono.error {
                    ApplicationException(
                        message = "No se encontraron productos para los filtros seleccionados.",
                        code = HttpStatus.NOT_FOUND,
                        cause = Throwable()
                    )
                }
            ).map { fromProductToProductResponse(it) }
    }

    fun findProductToppingsByMainDishId(mainDishId: String): Flux<ProductResponse> {
        return productDAO.findProductToppingsByMainDishId(mainDishId)
            .switchIfEmpty(
                Mono.error { exceptionService.throwNotFoundException("No se encontraron toppings para el producto sellecionado") }
            ).map { fromProductToProductResponse(it) }
    }

    fun findProductTypes(): Flux<ProductTypeResponse> {
        return productDAO.findAllProductsTypes()
            .switchIfEmpty {
                Flux.error<ProductTypeResponse> {
                    exceptionService.throwNotFoundException("No se encontraron tipos de productos.")
                }
            }.map {
                fromProductTypeToProductTypeResponse(it)
            }
    }
}