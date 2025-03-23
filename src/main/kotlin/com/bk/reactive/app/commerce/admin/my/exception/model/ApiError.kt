package com.bk.reactive.app.commerce.admin.my.exception.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import org.springframework.http.HttpStatus
import java.time.ZonedDateTime

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName(value = "error")
class ApiError(
    val detailMessage: String? = null,

    val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ")
    val timestamp: ZonedDateTime = ZonedDateTime.now(),

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val details: ArrayList<Error>,

    @JsonIgnore
    val debugMessage: String
)