package com.bk.reactive.app.commerce.admin.my.exception.api

import org.springframework.http.HttpStatus

open class CashException(
    code: HttpStatus,
    message: String,
    cause: Throwable = Throwable()
) : ApplicationException(code, message, cause)

open class SaveCashException(
    code: HttpStatus,
    message: String,
    val suppressedSaleException: Array<Throwable>
) : CashException(code, message)
