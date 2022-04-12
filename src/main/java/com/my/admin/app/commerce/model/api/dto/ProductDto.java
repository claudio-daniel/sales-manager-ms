package com.my.admin.app.commerce.model.api.dto;

import java.util.Objects;

public class ProductDto {

    private Long id;

    private String name;

    private String code;

    private Long productTypeId;

    private Double price;

    private Integer count;

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

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(final Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(final Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof ProductDto) {
            return this.id.equals(((ProductDto) o).getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
