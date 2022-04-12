package com.my.admin.app.commerce.model.transformer;

import com.black.food.manager.model.api.response.ProductResponse;
import com.black.food.manager.model.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductTransformer {
    public static final CommonTransformer<Product, ProductResponse> FROM_PRODUCT_TO_PRODUCT_RESPONSE;

    static {
        FROM_PRODUCT_TO_PRODUCT_RESPONSE = CommonTransformer.mapping(Product.class, ProductResponse.class)
                .constructor(ProductResponse::new)
                .fields(Product::getId, ProductResponse::setId)
                .fields(Product::getCode, ProductResponse::setCode)
                .fields(Product::getName, ProductResponse::setName)
                .fields(Product::getPrice, ProductResponse::setPrice)
                .fields(product -> ProductTypeTransformer.FROM_PRODUCT_TYPE_TO_PRODUCT_TYPE_RESPONSE.apply(product.getProductType()), ProductResponse::setProductType);
    }
}
