package com.bk.reactive.app.commerce.admin.my.exception.api

import org.springframework.http.HttpStatus

class InvalidExecutionException(
    val url: String,
    code: HttpStatus,
    message: String,
    cause: Throwable
) : ApplicationException(code, message, cause) {
}