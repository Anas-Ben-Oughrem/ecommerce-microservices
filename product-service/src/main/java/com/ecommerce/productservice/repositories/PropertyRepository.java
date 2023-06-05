package com.ecommerce.productservice.repositories;

import com.ecommerce.productservice.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property,Long> {
}
