package com.my.admin.app.commerce.model.service;

import com.my.admin.app.commerce.model.dao.RenterDao;
import com.my.admin.app.commerce.model.document.Renter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class RenterService {

    private static final Logger log = LoggerFactory.getLogger(RenterService.class);

    @Autowired
    private RenterDao renterDao;

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    public Flux<Renter> findAll() {
        return renterDao.findAll()
                .map(renter -> {
                    renter.setName(renter.getName().toUpperCase());
                    renter.setCreateAt(LocalDate.now());
                    return renter;
                })
                .doOnNext(inq -> log.info(inq.getName()));
    }

    public Mono<Renter> findRenterById(String id) {
        return this.renterDao.findById(id);
    }

    public Mono<Renter> saveRenter(Renter renter) {
        return this.renterDao.save(renter);
    }

    public void deleteRenterById(String id) {
        renterDao.deleteById(id);
    }

    public void dropInquilinosCollection() {
        reactiveMongoTemplate
                .dropCollection("renters")
                .subscribe();
    }
}
