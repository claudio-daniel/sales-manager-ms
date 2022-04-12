package com.my.admin.app.commerce.model.transformer;

import com.black.food.manager.model.api.response.ProductTypeResponse;
import com.black.food.manager.model.entity.ProductType;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeTransformer {

    public static final CommonTransformer<ProductType, ProductTypeResponse> FROM_PRODUCT_TYPE_TO_PRODUCT_TYPE_RESPONSE;

    static {
        FROM_PRODUCT_TYPE_TO_PRODUCT_TYPE_RESPONSE = CommonTransformer.mapping(ProductType.class, ProductTypeResponse.class)
                .constructor(ProductTypeResponse::new)
                .fields(ProductType::getId, ProductTypeResponse::setId)
                .fields(ProductType::getDescription, ProductTypeResponse::setDescription)
                .fields(ProductType::getName, ProductTypeResponse::setName)
                .fields(ProductType::getIcon, ProductTypeResponse::setIcon);
    }
}
