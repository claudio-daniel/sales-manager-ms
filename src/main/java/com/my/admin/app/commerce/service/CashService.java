package com.my.admin.app.commerce.service;

import com.black.food.manager.dao.CashDao;
import com.black.food.manager.dao.TurnDao;
import com.black.food.manager.dao.UserDao;
import com.black.food.manager.exception.ApplicationException;
import com.black.food.manager.helper.DateHelper;
import com.black.food.manager.model.api.request.cash.CreateCashRequest;
import com.black.food.manager.model.api.request.cash.UpdateCashRequest;
import com.black.food.manager.model.api.response.CashResponse;
import com.black.food.manager.model.api.response.CashStatusResponse;
import com.black.food.manager.model.entity.Cash;
import com.black.food.manager.model.entity.CashStatus;
import com.black.food.manager.model.entity.Turn;
import com.black.food.manager.model.entity.User;
import com.black.food.manager.model.transformer.CashTransformer;
import com.black.food.manager.service.validator.cash.CashFluxValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CashService {

    private CashDao cashDao;
    private UserDao userDao;
    private TurnDao turnDao;
    private DateHelper dateHelper;

    private CashFluxValidator cashFluxValidator;
    private CashTransformer cashTransformer;

    private static final Logger LOGGER = LoggerFactory.getLogger(CashService.class);

    public CashResponse createCash(final CreateCashRequest createCashRequest) {

        cashFluxValidator.validateRequestForCreate(createCashRequest);

        User user = findUserById(createCashRequest.getUserId());
        Turn turn = findTurnById(createCashRequest.getTurnId());

        Cash cash = new Cash();

        cash.setUser(user);
        cash.setTurn(turn);
        cash.setStartDate(getNowZonedDateTime());
        cash.setStartMoney(createCashRequest.getStartMoney());
        cash.setCashStatus(findCashStatus(createCashRequest.getStatusId()));

        return cashTransformer.apply(save(cash));
    }

    public CashResponse update(final String id, final UpdateCashRequest updateCashRequest) {
        cashFluxValidator.validateRequestForUpdate(updateCashRequest);

        Cash cashReturn;
        Cash cashToUpdate = findById(Long.valueOf(id));
        try {
            var cashStatus = updateCashRequest.getStatusId() == null
                    ? cashToUpdate.getCashStatus()
                    : findCashStatusById(Long.valueOf(updateCashRequest.getStatusId()));

            cashToUpdate.setStartMoney(updateCashRequest.getStartMoney());
            cashToUpdate.setStartDate(updateCashRequest.getStartDate());
            cashToUpdate.setCashStatus(cashStatus);

            cashReturn = save(cashToUpdate);
        } catch (Exception exception) {
            var error = String.format("Error while updating cash whit id %s", id);
            throw throwConflictException(exception.getLocalizedMessage(), error);
        }

        return cashTransformer.apply(cashReturn);
    }

    public Set<CashResponse> findAll() {
        return cashDao.findAll()
                .stream()
                .map(cash -> cashTransformer.apply(cash))
                .collect(Collectors.toSet());
    }

    public Cash findById(final Long id) {
        return cashDao.findById(id)
                .orElseThrow(() -> throwNotFoundException(id, "id"));
    }

    public Set<CashResponse> findByStatus(final Long cashStatusId) {
        var activeCashes = cashDao.findByStatus(cashStatusId);

        activeCashes
                .stream()
                .findFirst()
                .orElseThrow(() -> throwNotFoundException(cashStatusId, "status id"));

        return activeCashes
                .stream()
                .map(cash -> cashTransformer.apply(cash))
                .collect(Collectors.toSet());
    }

    public CashResponse findCashResponseById(final Long id) {
        return cashTransformer.apply(findById(id));
    }

    public CashResponse deleteCash(final String id) {

        Cash cash = findById(Long.valueOf(id));

        try {
            cashDao.delete(cash);
        } catch (Exception exception) {
            var messageError = exception.getLocalizedMessage();
            var error = String.format("Error while deleting cash with id %d.", cash.getId());
            throw throwConflictException(messageError, error);
        }

        return cashTransformer.apply(cash);
    }

    public Set<CashStatusResponse> findAllCashStatus() {
        return cashDao.findAllCashStatus()
                .stream()
                .map(cashStatus -> cashTransformer.transformCashStatusToCashStatusResponse(cashStatus))
                .collect(Collectors.toSet());
    }

    private Cash save(final Cash cash) {
        Cash createdCash;
        try {
            createdCash = cashDao.save(cash);
        } catch (Exception exception) {
            var error = "Error while saving cash.";
            throw throwConflictException(exception.getLocalizedMessage(), error);
        }
        return createdCash;
    }

    private CashStatus findCashStatus(final String cashStatusId) {
        return findCashStatusById(Long.valueOf(cashStatusId));
    }

    private CashStatus findCashStatusById(final Long cashStatusId) {
        return cashDao.findCashStatusById(cashStatusId)
                .orElseThrow(() -> throwNotFoundExceptionToCashStatus(cashStatusId, "id"));
    }

    private ZonedDateTime getNowZonedDateTime() {
        return dateHelper.getNowZonedDateTime();
    }

    public User findUserById(final String userId) {
        return userDao.findById(Long.valueOf(userId)).orElseThrow();
    }

    public Turn findTurnById(final String turnId) {
        return turnDao.findById(Long.valueOf(turnId)).orElseThrow();
    }

    private RuntimeException throwNotFoundException(final Long id, final String param) {
        var message = String.format("Cash with %s %s not found", param, id);
        LOGGER.error(message);
        throw new ApplicationException(message, HttpStatus.NOT_FOUND);
    }

    private RuntimeException throwNotFoundExceptionToCashStatus(final Long id, final String param) {
        var message = String.format("Cash status with %s %s not found", param, id);
        LOGGER.error(message);
        throw new ApplicationException(message, HttpStatus.NOT_FOUND);
    }

    private RuntimeException throwConflictException(final String message, final String error) {
        var completeMessage = String.format("Error : %s", message);
        LOGGER.error(error);
        LOGGER.error(completeMessage);
        throw new ApplicationException(completeMessage, HttpStatus.CONFLICT);
    }

    @Autowired
    public void setCashDao(final CashDao cashDao) {
        this.cashDao = cashDao;
    }

    @Autowired
    public void setUserDao(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setTurnDao(final TurnDao turnDao) {
        this.turnDao = turnDao;
    }

    @Autowired
    public void setDateHelper(final DateHelper dateHelper) {
        this.dateHelper = dateHelper;
    }

    @Autowired
    public void setCashFluxValidator(final CashFluxValidator cashFluxValidator) {
        this.cashFluxValidator = cashFluxValidator;
    }

    @Autowired
    public void setCashTransformer(final CashTransformer cashTransformer) {
        this.cashTransformer = cashTransformer;
    }
}
