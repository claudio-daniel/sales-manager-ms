package com.my.admin.app.commerce.model.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CashStatusResponse {

    @ApiModelProperty(value = "Cash Status Id", example = "1")
    private String id;

    @ApiModelProperty(value = "Cash Status name", example = "Activa")
    private String name;
}
