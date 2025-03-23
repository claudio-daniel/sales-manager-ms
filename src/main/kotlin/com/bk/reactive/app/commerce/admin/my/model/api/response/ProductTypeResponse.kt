package com.bk.reactive.app.commerce.admin.my.model.api.response

import com.fasterxml.jackson.annotation.JsonInclude
import io.swagger.annotations.ApiModelProperty

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class ProductTypeResponse(
    @ApiModelProperty(value = "Id of product type", example = "333")
    val id: String,

    @ApiModelProperty(value = "Name of product type", example = "Fast food")
    val name: String,

    @ApiModelProperty(value = "Description of product type", example = "Fast food")
    val description: String,

    @ApiModelProperty(value = "Icon of product type", example = "fast-food")
    val icon: String,
)