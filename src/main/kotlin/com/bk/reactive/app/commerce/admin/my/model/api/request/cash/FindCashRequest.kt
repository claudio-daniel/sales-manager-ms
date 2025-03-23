package com.bk.reactive.app.commerce.admin.my.model.api.request.cash

import io.swagger.annotations.ApiParam
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.RequestParam

class FindCashRequest(
    @ApiParam(value = "cash id", example = "1111")
    @RequestParam(value = "id", required = false) val id: String?,

    @ApiParam(value = "cash status id", example = "1111")
    @RequestParam(value = "cashStatusId", required = false) val cashStatusId: String?,

    @ApiParam(value = "turn id", example = "1111")
    @RequestParam(value = "turnId", required = false) val turnId: String?,

    @ApiParam(value = "user id", example = "1111")
    @RequestParam(value = "userId", required = false) val userId: String?,

    @ApiParam(value = "date from", example = "2021-12-01T19:40:47-03:00")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @RequestParam(value = "dateFrom", required = false) val dateFrom: String?,

    @ApiParam(value = "date to", example = "2021-12-01T19:40:47-03:00")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @RequestParam(value = "dateTo", required = false) val dateTo: String?
)