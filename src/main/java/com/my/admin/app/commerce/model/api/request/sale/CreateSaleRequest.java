package com.my.admin.app.commerce.model.api.request.sale;

import com.black.food.manager.model.api.request.client.CreateClientRequest;
import com.black.food.manager.model.api.request.order.CreateOrderRequest;

public class CreateSaleRequest {

    private CreateOrderRequest createOrderRequest;

    private Long userId;

    private CreateClientRequest createClientRequest;

    private Long paymentTypeId;

    private Long cashId;

    private Double payment;

    private Double returned;

    private Double total;

    public CreateOrderRequest getCreateOrderRequest() {
        return createOrderRequest;
    }

    public void setCreateOrderRequest(final CreateOrderRequest createOrderRequest) {
        this.createOrderRequest = createOrderRequest;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public CreateClientRequest getCreateClientRequest() {
        return createClientRequest;
    }

    public void setCreateClientRequest(final CreateClientRequest createClientRequest) {
        this.createClientRequest = createClientRequest;
    }

    public Long getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(final Long paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public Long getCashId() {
        return cashId;
    }

    public void setCashId(final Long cashId) {
        this.cashId = cashId;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(final Double payment) {
        this.payment = payment;
    }

    public Double getReturned() {
        return returned;
    }

    public void setReturned(final Double returned) {
        this.returned = returned;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(final Double total) {
        this.total = total;
    }
}
