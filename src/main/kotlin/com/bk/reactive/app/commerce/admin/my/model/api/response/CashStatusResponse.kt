package com.bk.reactive.app.commerce.admin.my.model.api.response

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.annotations.ApiModelProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class CashStatusResponse(
    @ApiModelProperty(value = "Cash Status Id", example = "1")
    val id: String,

    @ApiModelProperty(value = "Cash Status name", example = "Activa")
    val name: String
)