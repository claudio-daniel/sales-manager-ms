package com.bk.reactive.app.commerce.admin.my.exception.api

import org.springframework.http.HttpStatus

open class SaleException(
    code: HttpStatus,
    message: String,
    cause: Throwable = Throwable()
) : ApplicationException(code, message, cause)

open class SaveSaleException(
    code: HttpStatus,
    message: String,
    val suppressedSaleException: Array<Throwable>
) : SaleException(code, message)

open class NotFoundSaleException(
    code: HttpStatus,
    message: String,
) : SaleException(code, message)

open class FilterSaleException(
    code: HttpStatus,
    message: String,
) : SaleException(code, message)