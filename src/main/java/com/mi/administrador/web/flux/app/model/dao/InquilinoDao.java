package com.mi.administrador.web.flux.app.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mi.administrador.web.flux.app.model.document.Inquilino;
import com.mi.administrador.web.flux.app.model.repository.InquilinoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class InquilinoDao {

	@Autowired
	private InquilinoRepository inquilinoRepository;

	public Flux<Inquilino> findAll() {
		
		Flux<Inquilino> inquilinoFlux = inquilinoRepository.findAll();
		
		return inquilinoFlux;
	}
	
	public Mono<Inquilino> save(Inquilino inquilino) {
		return this.inquilinoRepository.save(inquilino);
	}
}
