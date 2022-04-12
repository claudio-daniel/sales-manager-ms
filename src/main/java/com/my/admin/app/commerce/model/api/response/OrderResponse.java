package com.my.admin.app.commerce.model.api.response;

import java.util.Set;

public class OrderResponse {

    private Long id;

    private Set<OrderItemResponse> orderItems;

    private String orderType;

    private Double total;

    public Long getId() { return id; }

    public void setId(final Long id) { this.id = id; }

    public Set<OrderItemResponse> getOrderItems() { return orderItems; }

    public void setOrderItems(final Set<OrderItemResponse> orderItems) { this.orderItems = orderItems; }

    public String getOrderType() { return orderType; }

    public void setOrderType(final String orderType) { this.orderType = orderType; }

    public Double getTotal() { return total; }

    public void setTotal(final Double total) { this.total = total; }
}
