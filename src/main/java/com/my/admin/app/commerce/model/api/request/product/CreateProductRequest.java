package com.my.admin.app.commerce.model.api.request.product;

public class CreateProductRequest {
    private Long id;
    private String name;
    private Double price;
    private String code;
    private Long productType;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public Long getProductType() {
        return productType;
    }

    public void setProductType(final Long productType) {
        this.productType = productType;
    }
}
