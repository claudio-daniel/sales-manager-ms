package com.my.admin.app.commerce.model.repository;

import com.my.admin.app.commerce.model.document.ConsumedService;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ConsumedServicesRepository extends ReactiveMongoRepository<ConsumedService, String> {
}
