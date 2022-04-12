package com.my.admin.app.commerce.service;

import com.black.food.manager.dao.TurnDao;
import com.black.food.manager.model.api.response.TurnResponse;
import com.black.food.manager.model.transformer.TurnTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TurnService {

    private TurnDao turnDao;

    private TurnTransformer turnTransformer;

    public Set<TurnResponse> findAllTurnResponse(){
        return turnDao.findAll()
                .stream()
                .map(turn -> turnTransformer.apply(turn))
                .collect(Collectors.toSet());
    }

    @Autowired
    public void setTurnDao(final TurnDao turnDao) {
        this.turnDao = turnDao;
    }

    @Autowired
    public void setTurnTransformer(final TurnTransformer turnTransformer) {
        this.turnTransformer = turnTransformer;
    }
}
