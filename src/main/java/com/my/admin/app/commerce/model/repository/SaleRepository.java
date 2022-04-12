package com.my.admin.app.commerce.model.repository;

import com.black.food.manager.model.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Set;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "select * from sales s\n"
            + "inner join cashes c on c.id = s.cash_id\n"
            + "inner join clients cli on cli.id = s.client_id\n"
            + "inner join orders o on o.id = s.order_id\n"
            + "inner join order_items oi on oi.order_id = o.id\n"
            + "inner join products p on p.id = oi.product_id\n"
            + "where s.date BETWEEN :dateFrom and :dateTo\n"
            + "    and (:userId is null or c.user_id = :userId)\n"
            + "    and (:clientDni is null or cli.dni = :clientDni)\n"
            + "    and (:productId is null or p.id = :productId)\n"
            + "    and (:productTypeId is null or p.product_type_id = :productTypeId)\n"
            + "    and (:paymentTypeId is null or s.payment_type_id = :paymentTypeId)\n", nativeQuery = true)
    Set<Sale> salesFilter(@Param("dateFrom") ZonedDateTime dateFrom, @Param("dateTo") ZonedDateTime dateTo, @Param("userId") Long userId,
                          @Param("clientDni") Integer clientDni, @Param("productId") Long productId,
                          @Param("productTypeId") Long productTypeId, @Param("paymentTypeId") Long paymentTypeId);
}

