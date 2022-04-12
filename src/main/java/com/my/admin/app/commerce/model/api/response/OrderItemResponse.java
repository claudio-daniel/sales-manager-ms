package com.my.admin.app.commerce.model.api.response;

public class OrderItemResponse {

    private Long id;

    private ProductResponse product;

    private Integer count;

    public Long getId() { return id; }

    public void setId(final Long id) { this.id = id; }

    public ProductResponse getProduct() { return product; }

    public void setProduct(final ProductResponse product) { this.product = product; }

    public Integer getCount() { return count; }

    public void setCount(final Integer count) { this.count = count; }
}
