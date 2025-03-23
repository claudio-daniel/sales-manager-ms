package com.bk.reactive.app.commerce.admin.my.controller

import com.bk.reactive.app.commerce.admin.my.model.api.response.ProductResponse
import com.bk.reactive.app.commerce.admin.my.model.api.response.ProductTypeResponse
import com.bk.reactive.app.commerce.admin.my.model.constant.HttpCodesConstant.Companion.STATUS_BAD_REQUEST
import com.bk.reactive.app.commerce.admin.my.model.constant.HttpCodesConstant.Companion.STATUS_FORBIDDEN
import com.bk.reactive.app.commerce.admin.my.model.constant.HttpCodesConstant.Companion.STATUS_OK
import com.bk.reactive.app.commerce.admin.my.service.ProductService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@Api(tags = ["Product Controller"], description = "Permite traer productos e insertar en la bd")
@RestController
class ProductController(
    private val productService: ProductService
) {
    private val logger = KotlinLogging.logger {}

    @ApiOperation(value = "Get all products for a product type", response = ProductResponse::class)
    @ApiResponses(
        value = [
            ApiResponse(code = STATUS_OK, message = "Ok"),
            ApiResponse(code = STATUS_BAD_REQUEST, message = "Bad Request"),
            ApiResponse(code = STATUS_FORBIDDEN, message = "Full authentication is required to access this resource")]
    )
    @GetMapping("products")
    fun findProducts(
        @ApiParam(value = "product type id", example = "1111")
        @RequestParam(value = "productTypeId", required = false) productTypeId: String?,

        @ApiParam(value = "product id", example = "1111")
        @RequestParam(value = "productId", required = false) productId: String?,

        @ApiParam(value = "name or code of product", example = "nnn fff sss")
        @RequestParam(value = "nameCode", required = false) nameCode: String?
    ): Flux<ProductResponse> {
        logger.info { "Searching products by filters." }
        return productService.productFilter(productId, productTypeId, nameCode)
    }

    @ApiOperation(value = "Get topics products by main dish", response = ProductResponse::class)
    @ApiResponses(
        value = [
            ApiResponse(code = STATUS_OK, message = "Ok"),
            ApiResponse(code = STATUS_BAD_REQUEST, message = "Bad Request"),
            ApiResponse(code = STATUS_FORBIDDEN, message = "Full authentication is required to access this resource")]
    )
    @GetMapping("products-toppings")
    fun findProductsToppings(
        @ApiParam(value = "id of main dish product", example = "111")
        @RequestParam(value = "mainDishId") mainDishId: String
    ): Flux<ProductResponse> {
        logger.info { "Searching topics products by mainDishId." }
        return productService.findProductToppingsByMainDishId(mainDishId)
    }

    @ApiOperation(value = "Get all product types", response = ProductResponse::class)
    @ApiResponses(
        value = [
            ApiResponse(code = STATUS_OK, message = "Ok"),
            ApiResponse(code = STATUS_BAD_REQUEST, message = "Bad Request"),
            ApiResponse(code = STATUS_FORBIDDEN, message = "Full authentication is required to access this resource")]
    )
    @GetMapping("product-types")
    fun findProductTypes(): Flux<ProductTypeResponse> {
        logger.info { "Searching  all product types." }

        return productService.findProductTypes()
    }
}