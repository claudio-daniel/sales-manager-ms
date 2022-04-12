package com.my.admin.app.commerce.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserResponse {

    @ApiModelProperty(value = "User Id", example = "1")
    private Long id;

    @ApiModelProperty(value = "User full name", example = "Full Name")
    private String fullName;

    @ApiModelProperty(value = "User name", example = "username")
    private String username;
}
