package com.bk.reactive.app.commerce.admin.my.model.api.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.annotations.ApiModelProperty
import java.time.ZonedDateTime

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class CashResponse(
    id: String,
    turn: TurnResponse,
    status: CashStatusResponse,
    user: UserResponse,

    @ApiModelProperty(value = "Start money of cash", example = "1.0")
    val startMoney: Double,

    @ApiModelProperty(value = "End money of cash", example = "1.0")
    val endMoney: Double?,

    @ApiModelProperty(value = "Start date of cash", example = "2021-05-23T12:45:58.100-03:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ")
    val startDate: ZonedDateTime,

    @ApiModelProperty(value = "End date of cash", example = "2021-05-23T12:45:58.100-03:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ")
    val endDate: ZonedDateTime?,

    @ApiModelProperty(value = "Partial balance of cash", example = "1.0")
    val partialBalance: Double
) : BasicCashResponse(id, turn, status, user)