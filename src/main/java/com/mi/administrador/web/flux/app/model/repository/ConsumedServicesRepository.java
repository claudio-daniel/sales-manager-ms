package com.mi.administrador.web.flux.app.model.repository;

import com.mi.administrador.web.flux.app.model.document.ConsumedService;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ConsumedServicesRepository extends ReactiveMongoRepository<ConsumedService, String> {
}
