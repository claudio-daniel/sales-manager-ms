package com.my.admin.app.commerce.model.repository;

import com.black.food.manager.model.entity.Order;
import com.black.food.manager.model.entity.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT ot FROM OrderType ot WHERE ot.id = :orderTypeId")
    OrderType findOrderTypeById(@Param("orderTypeId") Long orderTypeId);

    @Query("SELECT ot FROM OrderType ot")
    Set<OrderType> findAllOrderTypes();
}
