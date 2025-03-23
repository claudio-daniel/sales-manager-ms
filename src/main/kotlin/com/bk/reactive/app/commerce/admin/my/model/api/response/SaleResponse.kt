package com.bk.reactive.app.commerce.admin.my.model.api.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.annotations.ApiModelProperty
import java.time.ZonedDateTime

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class SaleResponse(
    @ApiModelProperty(value = "Id of sale", example = "333")
    val id: String,

    @ApiModelProperty(value = "Id of order", example = "333")
    val orderId: String,

    @ApiModelProperty(value = "Cash containing the sale", example = "cash")
    val cash: BasicCashResponse,

    @ApiModelProperty(value = "Payment of sale", example = "1.0")
    val payment: Double,

    @ApiModelProperty(value = "Returned of sale", example = "1.0")
    val returned: Double,

    @ApiModelProperty(value = "Total of sale", example = "1.0")
    val total: Double,

    @ApiModelProperty(value = "End date of cash", example = "2021-05-23T12:45:58.100-03:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ")
    val date: ZonedDateTime
)