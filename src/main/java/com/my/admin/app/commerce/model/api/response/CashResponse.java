package com.my.admin.app.commerce.model.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class CashResponse extends BasicCashResponse {

    @ApiModelProperty(value = "Start money of cash", example = "1.0")
    private Double startMoney;

    @ApiModelProperty(value = "End money of cash", example = "1.0")
    private Double endMoney;

    @ApiModelProperty(value = "Start date of cash", example = "2021-05-23T12:45:58.100-03:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ")
    private ZonedDateTime startDate;

    @ApiModelProperty(value = "End date of cash", example = "2021-05-23T12:45:58.100-03:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ")
    private ZonedDateTime endDate;
}
