package com.my.admin.app.commerce.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TurnResponse {

    @ApiModelProperty(value = "Turn Id", example = "1")
    private String id;

    @ApiModelProperty(value = "Turn name", example = "Tarde")
    private String name;
}
