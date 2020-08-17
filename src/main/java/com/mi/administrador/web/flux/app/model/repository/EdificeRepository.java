package com.mi.administrador.web.flux.app.model.repository;

import com.mi.administrador.web.flux.app.model.document.Edifice;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EdificeRepository extends ReactiveMongoRepository<Edifice, String> {
}
