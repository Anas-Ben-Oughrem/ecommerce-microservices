package com.ecommerce.productservice.repositories;

import com.ecommerce.productservice.models.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory,Long> {
}
