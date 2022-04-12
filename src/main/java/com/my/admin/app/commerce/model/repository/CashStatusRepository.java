package com.my.admin.app.commerce.model.repository;

import com.black.food.manager.model.entity.CashStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashStatusRepository extends JpaRepository<CashStatus, Long> {
}
