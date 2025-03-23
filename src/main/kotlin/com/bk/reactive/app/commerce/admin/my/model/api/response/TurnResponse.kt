package com.bk.reactive.app.commerce.admin.my.model.api.response

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.annotations.ApiModelProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class TurnResponse(
    @ApiModelProperty(value = "Turn Id", example = "1")
    val id: String,

    @ApiModelProperty(value = "Turn name", example = "Tarde")
    val name: String
)