package com.mi.administrador.web.flux.app.model.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.mi.administrador.web.flux.app.model.document.Renter;

public interface RenterRepository extends ReactiveMongoRepository<Renter, String>{

}
