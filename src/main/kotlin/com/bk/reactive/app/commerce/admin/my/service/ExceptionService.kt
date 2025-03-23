package com.bk.reactive.app.commerce.admin.my.service

import com.bk.reactive.app.commerce.admin.my.exception.api.ApplicationException
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class ExceptionService {
    private val logger = KotlinLogging.logger {}

    fun throwNotFoundException(message: String): RuntimeException {
        logger.error { message }
        throw ApplicationException(
            message = message,
            code = HttpStatus.NOT_FOUND,
            cause = Throwable(message)
        )
    }

    fun throwNotFoundException(id: String, param: String): ApplicationException {
        val message = "Document with $param: $id not found"
        logger.error { message }
        return ApplicationException(
            message = message,
            code = HttpStatus.NOT_FOUND,
            cause = Throwable(message)
        )
    }

    fun throwConflictException(message: String): RuntimeException {
        logger.error { message }
        throw ApplicationException(
            message = message,
            code = HttpStatus.CONFLICT,
            cause = Throwable(message)
        )
    }

    fun throwNotFoundExceptionToCashStatus(id: String, param: String): RuntimeException {
        val message = "Cash status with $param $id not found"
        logger.error { message }
        throw ApplicationException(
            message = message,
            code = HttpStatus.NOT_FOUND,
            cause = Throwable(message)
        )
    }

    fun throwConflictException(message: String, error: String): RuntimeException {
        val completeMessage = "Error : $message"
        logger.error { error }
        logger.error { completeMessage }
        throw ApplicationException(
            message = completeMessage,
            code = HttpStatus.CONFLICT,
            cause = Throwable(message)
        )
    }
}