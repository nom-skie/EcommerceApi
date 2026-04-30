package com.ws101.Alastoy_Espano.EcommerceApi.repository;

import com.ws101.Alastoy_Espano.EcommerceApi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
}