package com.my.admin.app.commerce.model.transformer;

import com.black.food.manager.model.api.response.TurnResponse;
import com.black.food.manager.model.entity.Turn;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;

@Component
public class TurnTransformer implements Function<Turn, TurnResponse> {

    @Override
    public TurnResponse apply(final Turn turn){
        TurnResponse turnResponse = new TurnResponse();
        turnResponse.setId(turn.getId().toString());
        turnResponse.setName(turn.getName());

        return turnResponse;
    }
}
