package com.mi.administrador.web.flux.app.model.dao;

import com.mi.administrador.web.flux.app.model.document.Edifice;
import com.mi.administrador.web.flux.app.model.repository.EdificeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class EdificeDao {

    @Autowired
    private EdificeRepository edificeRepository;

    public Flux<Edifice> findAll(){ return edificeRepository.findAll(); }

    public Mono<Edifice> save(Edifice edifice){ return edificeRepository.save(edifice);}

    public Mono<Edifice> findById(String id) { return edificeRepository.findById(id); }

    public Mono<Void> deleteById(String id) { return  edificeRepository.deleteById(id); }
}
