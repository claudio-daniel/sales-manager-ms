package com.my.admin.app.commerce.model.service;

import com.my.admin.app.commerce.model.dao.ConsumedServicesDao;
import com.my.admin.app.commerce.model.dao.EdificeDao;
import com.my.admin.app.commerce.model.document.ConsumedService;
import com.my.admin.app.commerce.model.document.Edifice;
import com.my.admin.app.commerce.model.response.EdificeResponse;
import com.my.admin.app.commerce.model.transformer.EdificeTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EdificeService {

    @Autowired
    private EdificeDao edificeDao;

    @Autowired
    private ConsumedServicesDao consumedServicesDao;

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    private EdificeTransformer edificeTransformer;

    public Mono<Edifice> save(Edifice edifice){ return edificeDao.save(edifice); }

    public Mono<Edifice> findById(String id) { return edificeDao.findById(id);}

    public Mono<Void> deleteById(String id) { return  edificeDao.deleteById(id); }

    public Flux<Edifice> findAll(){
        return edificeDao
                .findAll()
                .doOnNext(edificeTransformer::apply);
    }

    public Mono<EdificeResponse> save(EdificeResponse edificeResponse){
        return edificeDao
                .save(edificeTransformer.transformEdificeResponseToEdifice(edificeResponse))
                .map(edificeTransformer);
    }

    public Mono<ConsumedService> saveConsumedService(ConsumedService consumedService){
        return consumedServicesDao.save(consumedService);
    }

    public void dropEdificesCollection(){
        reactiveMongoTemplate
                .dropCollection("edifices")
                .subscribe();
    }
}
