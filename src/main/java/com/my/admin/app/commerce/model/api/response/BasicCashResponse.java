package com.my.admin.app.commerce.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BasicCashResponse {
    @ApiModelProperty(value = "Cash Id", example = "1")
    private String id;

    @ApiModelProperty(value = "Turn value for cash")
    private TurnResponse turn;

    @ApiModelProperty(value = "Cash Status description", example = "Active")
    private CashStatusResponse status;

    @ApiModelProperty(value = "User value of cash", example = "1")
    private UserResponse user;
}
