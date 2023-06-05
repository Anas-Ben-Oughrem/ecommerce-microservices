package com.ecommerce.productservice.repositories;

import com.ecommerce.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findbypage(PageRequest of);

    List<Product> chercher(String s);
}
