package com.my.admin.app.commerce.model.api.response;

public class ProductResponse {

    private Long id;

    private String code;

    private String name;

    private Double price;

    private Integer count;

    private ProductTypeResponse productType;

    public Long getId() { return id; }

    public void setId(final Long id) { this.id = id; }

    public String getCode() { return code; }

    public void setCode(final String code) { this.code = code; }

    public String getName() { return name; }

    public void setName(final String name) { this.name = name; }

    public Double getPrice() { return price; }

    public void setPrice(final Double price) { this.price = price; }

    public Integer getCount() { return count; }

    public void setCount(final Integer count) { this.count = count; }

    public ProductTypeResponse getProductType() { return productType; }

    public void setProductType(final ProductTypeResponse productType) { this.productType = productType; }
}
