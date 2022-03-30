package com.my.admin.app.commerce.model.repository;

import com.my.admin.app.commerce.model.document.Renter;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RenterRepository extends ReactiveMongoRepository<Renter, String> {
}
