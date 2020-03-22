package com.mi.administrador.web.flux.app.model.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;

import com.mi.administrador.web.flux.app.model.dao.RenterDao;
import com.mi.administrador.web.flux.app.model.document.Renter;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RenterService {

	private static final Logger log = LoggerFactory.getLogger(RenterService.class);
	
	@Autowired
	private RenterDao renterDao;
	
	@Autowired
	private ReactiveMongoTemplate reactiveMongoTemplate;
	
	public Flux<Renter> findAll(){
		return renterDao.findAll()
		.map(renter -> {
			renter.setName(renter.getName().toUpperCase());
			renter.setCreateAt(LocalDate.now());
			return renter;
			})
		.doOnNext(inq -> log.info(inq.getName()));
	}

	public Mono<Renter> findRenterById(String id){ return this.renterDao.findById(id); }

	public Mono<Renter> saveRenter(Renter renter){ return this.renterDao.save(renter); }

	public void deleteRenterById(String id) { renterDao.deleteById(id);}

	public void dropInquilinosCollection(){
		reactiveMongoTemplate
		.dropCollection("renters")
		.subscribe();
	}

}
