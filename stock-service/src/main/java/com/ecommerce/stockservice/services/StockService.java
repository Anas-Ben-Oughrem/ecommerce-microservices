package com.ecommerce.stockservice.services;

import com.ecommerce.stockservice.models.Stock;
import com.ecommerce.stockservice.repositories.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    public List<Stock> getAll(){
        return stockRepository.findAll() ;
    }

    public Stock getOneStock( Long id){
        return stockRepository.findById(id).orElse(null);
    }

    public Stock createStock (Stock s){
        return stockRepository.save(s);
    }

    public void deleteStock (Long id) {
        stockRepository.deleteById(id);
    }

    public Stock updateStock ( Stock s, Long id){
        Stock s1 = stockRepository.findById(id).orElse(null);
        if (s1 !=null){
            s.setId(id);
            return stockRepository.saveAndFlush(s);
        }
        else{
            throw new RuntimeException("fail");
        }
    }
}

