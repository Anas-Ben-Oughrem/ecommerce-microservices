package com.ecommerce.orderservice.repositories;

import com.ecommerce.orderservice.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
}
