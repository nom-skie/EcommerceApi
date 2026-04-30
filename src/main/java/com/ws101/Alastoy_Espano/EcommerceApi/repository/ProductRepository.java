package com.ws101.Alastoy_Espano.EcommerceApi.repository;

import com.ws101.Alastoy_Espano.EcommerceApi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByCategoryName(String name);

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max")
    List <Product> findByPriceRange(@Param("min") double min, @Param("max") double max);
}
