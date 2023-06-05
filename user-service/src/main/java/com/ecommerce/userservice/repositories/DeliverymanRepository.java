package com.ecommerce.userservice.repositories;

import com.ecommerce.userservice.models.Deliveryman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverymanRepository extends JpaRepository<Deliveryman,Long> {
}
