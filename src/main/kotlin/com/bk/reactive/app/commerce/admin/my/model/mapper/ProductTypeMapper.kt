package com.bk.reactive.app.commerce.admin.my.model.mapper

import com.bk.reactive.app.commerce.admin.my.model.api.response.ProductTypeResponse
import com.bk.reactive.app.commerce.admin.my.model.document.ProductType

class ProductTypeMapper {
    companion object {
        fun fromProductTypeToProductTypeResponse(productType: ProductType) =
            ProductTypeResponse(
                id = productType.id,
                name = productType.name,
                description = productType.description,
                icon = productType.icon,
            )
    }
}