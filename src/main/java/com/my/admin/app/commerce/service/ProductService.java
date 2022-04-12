package com.my.admin.app.commerce.service;

import com.black.food.manager.dao.ProductDao;
import com.black.food.manager.model.api.response.ProductResponse;
import com.black.food.manager.model.api.response.ProductTypeResponse;
import com.black.food.manager.model.entity.Product;
import com.black.food.manager.model.transformer.ProductTransformer;
import com.black.food.manager.model.transformer.ProductTypeTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductDao productDao;

    private ExceptionService exceptionService;

    public Product findById(final Long id) {
        return productDao.findById(id).orElseThrow();
    }

    public Set<ProductResponse> productFilter(final Long id, final Long productTypeId, final String nameCode) {
        var products = productDao.productFilter(id, productTypeId, nameCode);

        products.stream()
                .findFirst().orElseThrow(() -> exceptionService.throwNotFoundException("No se encontraron productos para los filtros seleccionados."));

        return products
                .stream()
                .map(ProductTransformer.FROM_PRODUCT_TO_PRODUCT_RESPONSE)
                .sorted((p1, p2) -> p1.getName().toUpperCase().compareTo(p2.getName()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Set<ProductResponse> findProductToppingsByMainDishId(final Long mainDishId) {
        return productDao.findProductToppingsByMainDishId(mainDishId);
    }

    public Set<ProductTypeResponse> findProductTypes() {
        var productTypeStream = productDao.findAllProductsTypes();

        productTypeStream.stream().findFirst().orElseThrow(() -> exceptionService.throwNotFoundException("No se encontraron tipos de productos"));

        return productTypeStream
                .stream()
                .map(ProductTypeTransformer.FROM_PRODUCT_TYPE_TO_PRODUCT_TYPE_RESPONSE)
                .collect(Collectors.toSet());
    }

    @Autowired
    public void setProductDao(final ProductDao productDao) {
        this.productDao = productDao;
    }

    @Autowired
    public void setExceptionService(final ExceptionService exceptionService) {
        this.exceptionService = exceptionService;
    }
}
