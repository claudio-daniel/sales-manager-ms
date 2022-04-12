package com.my.admin.app.commerce.model.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SaleResponse {

    @ApiModelProperty(value = "Id of sale", example = "333")
    private Long id;

    @ApiModelProperty(value = "User made the sale", example = "linus")
    private String userName;

    @ApiModelProperty(value = "Id of order", example = "333")
    private String orderId;

    @ApiModelProperty(value = "Cash containing the sale", example = "cash")
    private BasicCashResponse cash;

    @ApiModelProperty(value = "Payment of sale", example = "1.0")
    private Double payment;

    @ApiModelProperty(value = "Returned of sale", example = "1.0")
    private Double returned;

    @ApiModelProperty(value = "End date of cash", example = "2021-05-23T12:45:58.100-03:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ")
    private ZonedDateTime date;
}
