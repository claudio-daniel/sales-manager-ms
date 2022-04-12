package com.my.admin.app.commerce.model.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductTypeResponse {

    @ApiModelProperty(value = "Id of product type", example = "333")
    private Long id;

    @ApiModelProperty(value = "Name of product type", example = "Fast food")
    private String name;

    @ApiModelProperty(value = "Description of product type", example = "Fast food")
    private String description;

    @ApiModelProperty(value = "Icon of product type", example = "fast-food")
    private String icon;
}
