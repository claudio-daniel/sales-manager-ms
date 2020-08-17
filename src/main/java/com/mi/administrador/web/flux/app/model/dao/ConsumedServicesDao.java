package com.mi.administrador.web.flux.app.model.dao;

import com.mi.administrador.web.flux.app.model.document.ConsumedService;
import com.mi.administrador.web.flux.app.model.repository.ConsumedServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ConsumedServicesDao {

    @Autowired
    private ConsumedServicesRepository consumedServicesRepository;

    public Mono<ConsumedService> save(ConsumedService consumedService) { return consumedServicesRepository.save(consumedService); }
}
