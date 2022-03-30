package com.my.admin.app.commerce.model.repository;

import com.my.admin.app.commerce.model.document.Edifice;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EdificeRepository extends ReactiveMongoRepository<Edifice, String> {
}
