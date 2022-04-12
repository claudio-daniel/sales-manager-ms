package com.my.admin.app.commerce.service;

import com.black.food.manager.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ExceptionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionService.class);

    public RuntimeException throwNotFoundException(final String message) {
        LOGGER.error(message);
        throw new ApplicationException(message, HttpStatus.NOT_FOUND);
    }

    public RuntimeException throwConflictException(final String message) {
        LOGGER.error(message);
        throw new ApplicationException(message, HttpStatus.CONFLICT);
    }
}
