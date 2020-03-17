package com.mi.administrador.web.flux.app.model.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.mi.administrador.web.flux.app.model.document.Inquilino;

public interface InquilinoRepository extends ReactiveMongoRepository<Inquilino, String>{

}
