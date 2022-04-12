package com.my.admin.app.commerce.model.transformer;

import com.black.food.manager.model.api.response.*;
import com.black.food.manager.model.entity.*;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CashTransformer implements Function<Cash, CashResponse> {
    public static final CommonTransformer<Cash, BasicCashResponse> FROM_CASH_TO_BASIC_CASH_RESPONSE;
    public static final CommonTransformer<CashStatus, CashStatusResponse> FROM_CASH_STATUS_TO_CASH_STATUS_RESPONSE;
    public static final CommonTransformer<Turn, TurnResponse> FROM_TURN_TO_TURN_RESPONSE;

    static {
        FROM_CASH_TO_BASIC_CASH_RESPONSE = CommonTransformer.mapping(Cash.class, BasicCashResponse.class)
                .constructor(BasicCashResponse::new)
                .fields(cash -> cash.getId().toString(), BasicCashResponse::setId)
                .fields(cash -> CashTransformer.FROM_TURN_TO_TURN_RESPONSE.apply(cash.getTurn()), BasicCashResponse::setTurn)
                .fields(cash -> CashTransformer.FROM_CASH_STATUS_TO_CASH_STATUS_RESPONSE.apply(cash.getCashStatus()), BasicCashResponse::setStatus);

        FROM_CASH_STATUS_TO_CASH_STATUS_RESPONSE =
                CommonTransformer.mapping(CashStatus.class, CashStatusResponse.class)
                        .constructor(CashStatusResponse::new)
                        .fields(CashStatus::getName, CashStatusResponse::setName)
                        .fields(cashStatus -> cashStatus.getId().toString(), CashStatusResponse::setId);

        FROM_TURN_TO_TURN_RESPONSE = CommonTransformer.mapping(Turn.class, TurnResponse.class)
                .constructor(TurnResponse::new)
                .fields(Turn::getName, TurnResponse::setName)
                .fields(turn -> turn.getId().toString(), TurnResponse::setId);
    }

    @Override
    public CashResponse apply(final Cash cash) {
        var cashResponse = new CashResponse();
        cashResponse.setId(cash.getId().toString());
        cashResponse.setTurn(createTurn(cash.getTurn()));
        cashResponse.setEndDate(cash.getEndDate());
        cashResponse.setStartDate(cash.getStartDate());
        cashResponse.setStartMoney(cash.getStartMoney());
        cashResponse.setEndMoney(cash.getEndMoney());
        cashResponse.setStatus(transformCashStatusToCashStatusResponse(cash.getCashStatus()));
        cashResponse.setUser(createUser(cash.getUser()));
        return cashResponse;
    }

    private UserResponse createUser(final User user) {
        var userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUserName());
        userResponse.setFullName(user.getName().concat(" ").concat(user.getLastName()));
        return userResponse;
    }

    private TurnResponse createTurn(final Turn turn) {
        var turnResponse = new TurnResponse();
        turnResponse.setId(turn.getId().toString());
        turnResponse.setName(turn.getName());
        return turnResponse;
    }

    public CashStatusResponse transformCashStatusToCashStatusResponse(final CashStatus cashStatus) {
        var cashStatusResponse = new CashStatusResponse();
        cashStatusResponse.setId(cashStatus.getId().toString());
        cashStatusResponse.setName(cashStatus.getName());

        return cashStatusResponse;
    }
}
