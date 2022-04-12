package com.my.admin.app.commerce.model.api.request.cash;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
public class UpdateCashRequest {

    @NotNull(message = "{cash.start.money.not.null}")
    @ApiModelProperty(value = "Start money of cash", example = "1.0")
    private Double startMoney;

    @NotNull(message = "{cash.start.date.not.null}")
    @ApiModelProperty(value = "Cash start date", dataType = "String", example = "2021-05-23T12:45:58.100-03:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ")
    private ZonedDateTime startDate;

    @ApiModelProperty(value = "Status id of cash", example = "1")
    private String statusId;
}
