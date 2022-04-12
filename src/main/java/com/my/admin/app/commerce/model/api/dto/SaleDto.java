package com.my.admin.app.commerce.model.api.dto;

public class SaleDto {

    private Long id;

    private OrderDto orderDto;

    private Long userId;

    private ClientDto clientDto;

    private Long paymentTypeId;

    private Long cashId;

    private Double payment;

    private Double returned;

    private Double total;

    public Long getId() { return id; }

    public void setId(final Long id) { this.id = id; }

    public OrderDto getOrderDto() { return orderDto; }

    public void setOrderDto(final OrderDto orderDto) { this.orderDto = orderDto; }

    public Long getUserId() { return userId; }

    public void setUserId(final Long userId) { this.userId = userId; }

    public ClientDto getClientDto() { return clientDto; }

    public void setClientDto(final ClientDto clientId) { this.clientDto = clientId; }

    public Long getPaymentTypeId() { return paymentTypeId; }

    public void setPaymentTypeId(final Long paymentTypeId) { this.paymentTypeId = paymentTypeId; }

    public Long getCashId() { return cashId; }

    public void setCashId(final Long cashId) { this.cashId = cashId; }

    public Double getPayment() { return payment; }

    public void setPayment(final Double payment) { this.payment = payment; }

    public Double getReturned() { return returned; }

    public void setReturned(final Double returned) { this.returned = returned; }

    public Double getTotal() { return total; }

    public void setTotal(final Double total) { this.total = total; }
}
