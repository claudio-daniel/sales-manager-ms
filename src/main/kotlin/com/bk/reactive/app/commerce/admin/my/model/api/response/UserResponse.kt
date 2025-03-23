package com.bk.reactive.app.commerce.admin.my.model.api.response

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.annotations.ApiModelProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class UserResponse(
    @ApiModelProperty(value = "User Id", example = "1")
    val id: String,

    @ApiModelProperty(value = "User full name", example = "Full Name")
    val fullName: String,

    @ApiModelProperty(value = "User name", example = "username")
    val username: String
)