package com.my.admin.app.commerce.model.api.request.order;

public class CreateOrderItemsRequest {

    private Long productId;
    private Integer count;

    public Long getProductId() { return productId; }

    public void setProductId(final Long productId) { this.productId = productId; }

    public Integer getCount() {
        return count;
    }

    public void setCount(final Integer count) {
        this.count = count;
    }
}
