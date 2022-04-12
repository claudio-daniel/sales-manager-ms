package com.my.admin.app.commerce.model.repository;

import com.black.food.manager.model.entity.Cash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CashRepository extends JpaRepository<Cash, Long> {
    Set<Cash> findByCashStatusId(Long cashStatusId);
}
