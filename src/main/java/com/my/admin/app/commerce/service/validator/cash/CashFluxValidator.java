package com.my.admin.app.commerce.service.validator.cash;

import com.black.food.manager.model.api.request.cash.CreateCashRequest;
import com.black.food.manager.model.api.request.cash.UpdateCashRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CashFluxValidator {

    private CashStatusValidator cashStatusValidator;

    public void validateRequestForCreate(final CreateCashRequest createCashRequest){
        cashStatusValidator.validateCashStatusRequest(createCashRequest.getStatusId());
    }

    public void validateRequestForUpdate(final UpdateCashRequest updateCashRequest){
        cashStatusValidator.validateCashStatusRequest(updateCashRequest.getStatusId());
    }

    @Autowired
    public void setCashStatusValidator(final CashStatusValidator cashStatusValidator) {
        this.cashStatusValidator = cashStatusValidator;
    }
}
