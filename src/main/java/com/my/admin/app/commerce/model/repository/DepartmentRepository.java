package com.my.admin.app.commerce.model.repository;

import com.my.admin.app.commerce.model.document.Department;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DepartmentRepository extends ReactiveMongoRepository<Department, String> {
}
