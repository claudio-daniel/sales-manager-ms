package com.my.admin.app.commerce.model.api.request.cash;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateCashRequest {


    @ApiModelProperty(value = "Id unique of cash", example = "1")
    private String id;

    @NotNull(message = "{cash.user.not.null}")
    @ApiModelProperty(value = "User id of cash", example = "1")
    private String userId;

    @NotNull(message = "{cash.turn.not.null}")
    @ApiModelProperty(value = "Turn id of cash", example = "1")
    private String turnId;

    @NotNull(message = "{cash.start.money.not.null}")
    @ApiModelProperty(value = "Start money of cash", example = "1.0")
    private Double startMoney;

    @NotNull(message = "{cash.status.not.null}")
    @ApiModelProperty(value = "Status id of cash", example = "1")
    private String statusId;
}
