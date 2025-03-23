package com.bk.reactive.app.commerce.admin.my.controller

import com.bk.reactive.app.commerce.admin.my.model.api.response.TurnResponse
import com.bk.reactive.app.commerce.admin.my.model.constant.HttpCodesConstant.Companion.STATUS_BAD_REQUEST
import com.bk.reactive.app.commerce.admin.my.model.constant.HttpCodesConstant.Companion.STATUS_FORBIDDEN
import com.bk.reactive.app.commerce.admin.my.model.constant.HttpCodesConstant.Companion.STATUS_OK
import com.bk.reactive.app.commerce.admin.my.service.TurnService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@Api(tags = ["Turn Controller"], value = "Allows you to manage shifts.")
@RestController
class TurnController(
    private val turnService: TurnService
) {
    private val logger = KotlinLogging.logger {}

    @ApiOperation(value = "Get all turns.", response = TurnResponse::class)
    @ApiResponses(
        value = [
            ApiResponse(code = STATUS_OK, message = "Ok"),
            ApiResponse(code = STATUS_BAD_REQUEST, message = "Bad Request"),
            ApiResponse(code = STATUS_FORBIDDEN, message = "Full authentication is required to access this resource")
        ]
    )
    @GetMapping("turn")
    fun getAllTurns(): Flux<TurnResponse> {
        return turnService.findAllTurnResponse()
            .doOnTerminate { logger.info { "Returned all flux success" } }

    }
}