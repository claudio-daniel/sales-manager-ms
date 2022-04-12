package com.my.admin.app.commerce.model.api.request.order;

import java.util.Set;

public class CreateOrderRequest {
    private Set<CreateOrderItemsRequest> createOrderItemsRequest;

    private Long orderTypeId;

    public Set<CreateOrderItemsRequest> getCreateOrderItemsRequest() {
        return createOrderItemsRequest;
    }

    public void setCreateOrderItemsRequest(final Set<CreateOrderItemsRequest> createOrderItemsRequest) {
        this.createOrderItemsRequest = createOrderItemsRequest;
    }

    public Long getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderTypeId(final Long orderTypeId) {
        this.orderTypeId = orderTypeId;
    }
}
