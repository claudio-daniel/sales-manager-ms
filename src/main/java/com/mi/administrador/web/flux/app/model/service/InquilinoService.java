package com.mi.administrador.web.flux.app.model.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;

import com.mi.administrador.web.flux.app.model.dao.InquilinoDao;
import com.mi.administrador.web.flux.app.model.document.Inquilino;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InquilinoService {

	private static final Logger log = LoggerFactory.getLogger(InquilinoService.class);
	
	@Autowired
	private InquilinoDao inquilinoDao;
	
	@Autowired
	private ReactiveMongoTemplate reactiveMongoTemplate;
	
	public Flux<Inquilino> findAll(){
		return inquilinoDao.findAll()
		.map(inquilino -> {
			inquilino.setNombre(inquilino.getNombre().toUpperCase());
			inquilino.setCreateAt(LocalDate.now());
			return inquilino;
			})
		.doOnNext(inq -> log.info(inq.getNombre()));
	}
	
	public Mono<Inquilino> saveInquilino(Inquilino inquilino){
		return this.inquilinoDao.save(inquilino);
	}

	public void dropInquilinosCollection(){
		reactiveMongoTemplate
		.dropCollection("inquilinos")
		.subscribe();
	}
}
