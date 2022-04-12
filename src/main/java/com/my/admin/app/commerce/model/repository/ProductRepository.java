package com.my.admin.app.commerce.model.repository;

import com.black.food.manager.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Set<Product> findByProductTypeId(Long productTypeId);

    @Query("SELECT topping\n"
            + "FROM  ProductTopping pt\n"
            + "JOIN  Product mainDish on mainDish.id = pt.mainDishId\n"
            + "JOIN  Product topping on topping.id = pt.toppingId\n"
            + "WHERE mainDish.id = :mainDishId")
    Set<Product> findProductToppingsByMainDishId(@Param("mainDishId") Long mainDishId);

    @Query("SELECT product\n"
            + "FROM  Product product\n"
            + "WHERE (:id is null or product.id = :id) "
            + "and (:productTypeId is null or product.productType.id = :productTypeId) "
            + "and (:nameCode is null or (product.name like %:nameCode% or product.code like %:nameCode%)) ")
    Set<Product> productFilter(@Param("id") Long id, @Param("productTypeId") Long productTypeId, @Param("nameCode") String nameCode);
}
