package com.bk.reactive.app.commerce.admin.my.exception

import com.bk.reactive.app.commerce.admin.my.exception.api.ApplicationException
import com.bk.reactive.app.commerce.admin.my.exception.api.CashException
import com.bk.reactive.app.commerce.admin.my.exception.api.InvalidExecutionException
import com.bk.reactive.app.commerce.admin.my.exception.api.SaleException
import com.bk.reactive.app.commerce.admin.my.exception.api.SaveSaleException
import com.bk.reactive.app.commerce.admin.my.exception.model.ApiError
import com.bk.reactive.app.commerce.admin.my.exception.model.Error
import mu.KotlinLogging
import org.springframework.dao.DuplicateKeyException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ServerWebInputException
import java.util.concurrent.CompletionException

@ControllerAdvice
class ServiceExceptionHandler {
    private val logger = KotlinLogging.logger {}

    @ExceptionHandler(DuplicateKeyException::class)
    fun handleDuplicateKeyException(ex: DuplicateKeyException): ResponseEntity<ApiError> {
        logger.error { "Duplicate key error - Response status ${HttpStatus.CONFLICT}" }
        return buildBasicError(ex, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(ServerWebInputException::class)
    fun handleMissingServletRequestParameterException(ex: Exception): ResponseEntity<ApiError> {
        logger.error { "Missing Request Parameter error - Response status ${HttpStatus.BAD_REQUEST}" }
        logger.error { ex.localizedMessage }
        return buildBasicError(ex, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ApiError> {
        return if (ex.bindingResult.fieldError != null && ex.bindingResult.fieldError!!.defaultMessage != null) {
            logger.error { "Request error - Response status ${HttpStatus.BAD_REQUEST}" }
            logger.error { ex.localizedMessage }

            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ApiError(
                    status = HttpStatus.BAD_REQUEST,
                    details = processFieldErrors(ex.bindingResult.fieldErrors),
                    debugMessage = HttpStatus.BAD_REQUEST.toString()
                )
            )
        } else {
            logger.error { "Request error - Response status ${HttpStatus.CONFLICT}" }
            logger.error { ex.localizedMessage }

            ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                    ApiError(
                        status = HttpStatus.CONFLICT,
                        details = processFieldErrors(ex.bindingResult.fieldErrors),
                        debugMessage = HttpStatus.CONFLICT.toString()
                    )
                )
        }
    }

    @ExceptionHandler(InvalidExecutionException::class)
    fun handleInvalidExecution(ex: InvalidExecutionException?): ResponseEntity<ApiError> {
        val status = ex!!.code
        logger.error { "Invalid execution error - Response status $status - Request: ${ex.url}" }
        logger.error { ex.localizedMessage }

        return ResponseEntity
            .status(status)
            .body(
                ApiError(
                    status = status,
                    details = arrayListOf(
                        Error(
                            message = ex.localizedMessage,
                            field = ex.url,
                        )
                    ),
                    debugMessage = status.toString()
                )
            )
    }

    @ExceptionHandler(ApplicationException::class)
    fun handleApplicationException(ex: ApplicationException): ResponseEntity<ApiError> {
        logger.error { "Error - Response status ${ex.code}" }
        logger.error { ex.localizedMessage }

        return buildBasicError(ex, ex.code)
    }

    @ExceptionHandler(CompletionException::class)
    fun handleCompletionException(ex: CompletionException): ResponseEntity<ApiError> {
        return if (ex.cause is InvalidExecutionException) {
            handleInvalidExecution(ex.cause as InvalidExecutionException)
        } else {
            logger.error { "Completion error - Response status ${HttpStatus.INTERNAL_SERVER_ERROR}" }
            logger.error { ex.localizedMessage }

            buildBasicError(ex, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ApiError> {
        logger.error { "Error - Response status ${HttpStatus.INTERNAL_SERVER_ERROR}" }
        logger.error { ex.localizedMessage }

        return buildBasicError(ex, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(NumberFormatException::class)
    fun handleNumberException(ex: NumberFormatException): ResponseEntity<ApiError> {
        logger.error { "Invalid cast Error- Response status ${HttpStatus.BAD_REQUEST} " }
        logger.error { ex.localizedMessage }

        return buildBasicError(ex, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(CashException::class)
    fun handleCashException(cashException: CashException): ResponseEntity<ApiError> {
        logger.error { "Invalid cash request exception - Response status ${cashException.code}" }
        logger.error { cashException.localizedMessage }

        return buildBasicError(cashException, cashException.code)
    }

    @ExceptionHandler(SaleException::class)
    fun handleSaleException(saleException: SaleException): ResponseEntity<ApiError> {
        logger.error { "${saleException.javaClass} - Response status ${saleException.code}" }
        logger.error { saleException.localizedMessage }

        if (saleException is SaveSaleException && saleException.suppressedSaleException.isNotEmpty()) {
            return ResponseEntity
                .status(saleException.code)
                .body(
                    ApiError(
                        detailMessage = saleException.message,
                        status = saleException.code,
                        details = processMultipleException(
                            multipleException = saleException.suppressedSaleException
                        ),
                        debugMessage = saleException.toString()
                    )
                )
        }

        return buildBasicError(saleException, saleException.code)
    }

    private fun buildBasicError(ex: Exception, statusCode: HttpStatus): ResponseEntity<ApiError> {
        val apiError = ApiError(
            status = statusCode,
            details = arrayListOf(Error(ex.localizedMessage)),
            debugMessage = statusCode.toString()

        )
        return ResponseEntity.status(statusCode).body(apiError)
    }

    private fun processFieldErrors(fieldErrors: List<FieldError>): ArrayList<Error> {
        return ArrayList(
            fieldErrors.map {
                Error(
                    code = HttpStatus.BAD_REQUEST.toString(),
                    message = it.defaultMessage!!
                )
            })
    }

    private fun processMultipleException(multipleException: Array<Throwable>): ArrayList<Error> {
        return ArrayList(
            multipleException.map { Error(message = it.localizedMessage) }
        )
    }
}