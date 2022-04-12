package com.my.admin.app.commerce.model.api.dto;

import lombok.Data;

@Data
public class CashDto {
    private Long id;

    private Long userId;

    private Long turnId;

    private Double startMoney;
}
