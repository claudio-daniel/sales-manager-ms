package com.bk.reactive.app.commerce.admin.my.model.mapper

import com.bk.reactive.app.commerce.admin.my.model.api.response.ProductResponse
import com.bk.reactive.app.commerce.admin.my.model.document.Product
import com.bk.reactive.app.commerce.admin.my.model.mapper.ProductTypeMapper.Companion.fromProductTypeToProductTypeResponse

class ProductMapper {
    companion object {
        fun fromProductToProductResponse(product: Product) =
            ProductResponse(
                id = product.id,
                code = product.code,
                name = product.name,
                price = product.price,
                productType = fromProductTypeToProductTypeResponse(product.productType)
            )
    }
}