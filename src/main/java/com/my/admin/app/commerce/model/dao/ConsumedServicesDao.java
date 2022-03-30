package com.my.admin.app.commerce.model.dao;

import com.my.admin.app.commerce.model.document.ConsumedService;
import com.my.admin.app.commerce.model.repository.ConsumedServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ConsumedServicesDao {

    @Autowired
    private ConsumedServicesRepository consumedServicesRepository;

    public Mono<ConsumedService> save(ConsumedService consumedService) { return consumedServicesRepository.save(consumedService); }
}
