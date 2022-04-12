package com.my.admin.app.commerce.model.api.dto;

import java.util.Set;

public class OrderDto {

    private Long id;

    private Set<DishDto> dishes;

    private Long orderTypeId;

    public Long getId() { return id; }

    public void setId(final Long id) { this.id = id; }

    public Set<DishDto> getDishes() { return dishes; }

    public void setDish(final Set<DishDto> dishes) { this.dishes = dishes; }

    public Long getOrderTypeId() { return orderTypeId; }

    public void setOrderTypeId(final Long orderTypeId) { this.orderTypeId = orderTypeId; }
}
