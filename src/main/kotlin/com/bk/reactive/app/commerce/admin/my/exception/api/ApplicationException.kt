package com.bk.reactive.app.commerce.admin.my.exception.api

import org.springframework.http.HttpStatus

/**
 * Base Application Exception
 */
open class ApplicationException(
    val code: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    message: String,
    cause: Throwable
) : RuntimeException(message, cause)