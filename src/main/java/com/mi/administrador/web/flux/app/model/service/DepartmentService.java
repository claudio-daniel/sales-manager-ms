package com.mi.administrador.web.flux.app.model.service;

import com.mi.administrador.web.flux.app.model.dao.DepartmentDao;
import com.mi.administrador.web.flux.app.model.document.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class DepartmentService {
    private static final Logger log = LoggerFactory.getLogger(RenterService.class);

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    public Flux<Department> findAll(){
        return departmentDao.findAll()
                .map(renter -> {
                    renter.setName(renter.getName().toUpperCase());
                    renter.setCreateAt(LocalDate.now());
                    return renter;
                })
                .doOnNext(inq -> log.info(inq.getName()));
    }

    public Mono<Department> findDepartmentById(String id){ return this.departmentDao.findById(id); }

    public Mono<Department> saveDepartment(Department department){ return this.departmentDao.save(department); }

    public void deleteDepartmentById(String id) { departmentDao.deleteById(id);}

    public void dropDepartmentCollection(){
        reactiveMongoTemplate
                .dropCollection("department")
                .subscribe();
    }

}

