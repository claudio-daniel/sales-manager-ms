package com.my.admin.app.commerce.service.validator.cash;

import com.black.food.manager.exception.CashException;
import com.black.food.manager.exception.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.black.food.manager.model.constant.BusinessModelConstant.*;

@Component
public class CashStatusValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CashStatusValidator.class);

    public void validateCashStatusRequest(final String statusId) {
        if (statusId.equalsIgnoreCase(CASH_STATUS_INACTIVE_LONG.toString())) {
            LOGGER.error(ErrorCode.INVALID_CASH_STATUS.getDescription());
            throw  new CashException(ErrorCode.INVALID_CASH_STATUS.getDescription(), HttpStatus.CONFLICT);
        }
    }
}
