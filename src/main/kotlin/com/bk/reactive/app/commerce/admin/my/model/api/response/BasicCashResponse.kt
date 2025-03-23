package com.bk.reactive.app.commerce.admin.my.model.api.response

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.annotations.ApiModelProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
open class BasicCashResponse(
    @ApiModelProperty(value = "Cash Id", example = "1")
    val id: String,

    @ApiModelProperty(value = "Turn value for cash")
    val turn: TurnResponse,

    @ApiModelProperty(value = "Cash Status description", example = "Active")
    val status: CashStatusResponse,

    @ApiModelProperty(value = "User value of cash", example = "1")
    val user: UserResponse
)