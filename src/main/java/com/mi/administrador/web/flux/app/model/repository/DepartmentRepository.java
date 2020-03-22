package com.mi.administrador.web.flux.app.model.repository;

import com.mi.administrador.web.flux.app.model.document.Department;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DepartmentRepository extends ReactiveMongoRepository<Department, String> {
}
