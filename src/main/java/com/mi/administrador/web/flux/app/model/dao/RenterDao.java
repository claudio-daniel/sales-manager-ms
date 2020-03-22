package com.mi.administrador.web.flux.app.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mi.administrador.web.flux.app.model.document.Renter;
import com.mi.administrador.web.flux.app.model.repository.RenterRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RenterDao {

	@Autowired
	private RenterRepository renterRepository;

	public Flux<Renter> findAll() { return renterRepository.findAll(); }

	public Mono<Renter> findById(String id) { return this.renterRepository.findById(id); }

	public Mono<Renter> save(Renter renter) {
		return this.renterRepository.save(renter);
	}

	public void deleteById(String id) { this.renterRepository.deleteById(id); }
}
