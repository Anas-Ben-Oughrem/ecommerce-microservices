package com.ecommerce.stockservice.repositories;

import com.ecommerce.stockservice.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {
}
