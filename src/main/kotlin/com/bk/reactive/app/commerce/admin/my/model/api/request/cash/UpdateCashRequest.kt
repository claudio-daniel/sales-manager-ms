package com.bk.reactive.app.commerce.admin.my.model.api.request.cash

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import java.time.ZonedDateTime
import jakarta.validation.constraints.NotNull

class UpdateCashRequest(
    @ApiModelProperty(value = "Start money of cash", example = "1.0")
    val startMoney: @NotNull(message = "{cash.start.money.not.null}") Double,

    @ApiModelProperty(value = "Cash start date", dataType = "String", example = "2021-05-23T12:45:58.100-03:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ")
    val startDate: @NotNull(message = "{cash.start.date.not.null}") ZonedDateTime,

    @ApiModelProperty(value = "Status id of cash", example = "1")
    val statusId: String,

    @ApiModelProperty(value = "User id of cash", example = "001")
    val userId: String
)