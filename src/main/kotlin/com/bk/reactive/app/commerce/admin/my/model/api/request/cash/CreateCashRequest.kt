package com.bk.reactive.app.commerce.admin.my.model.api.request.cash

import io.swagger.annotations.ApiModelProperty
import jakarta.validation.constraints.NotNull

class CreateCashRequest(
    @ApiModelProperty(value = "Id unique of cash", example = "1")
    val id: String?,

    @ApiModelProperty(value = "User id of cash", example = "1")
    val userId: @NotNull(message = "{cash.user.not.null}") String,

    @ApiModelProperty(value = "Turn id of cash", example = "1")
    val turnId: @NotNull(message = "{cash.turn.not.null}") String,

    @ApiModelProperty(value = "Start money of cash", example = "1.0")
    val startMoney: @NotNull(message = "{cash.start.money.not.null}") Double,

    @ApiModelProperty(value = "Status id of cash", example = "1")
    val statusId: @NotNull(message = "{cash.status.not.null}") String
)