package com.my.admin.app.commerce.model.dao;

import com.my.admin.app.commerce.model.document.Edifice;
import com.my.admin.app.commerce.model.repository.EdificeRepository;
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
