package com.ecommerce.stockservice.controllers;

import com.ecommerce.stockservice.models.Stock;
import com.ecommerce.stockservice.services.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin("*")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping
    public List<Stock> getAll(){
        return stockService.getAll() ;
    }

    @GetMapping("/{id}")
    public Stock getOneStock(@PathVariable Long id){
        return stockService.getOneStock(id);
    }

    @PostMapping
    public Stock createStock (@RequestBody Stock stock){
        return stockService.createStock(stock);
    }

    @DeleteMapping("/{id}")
    public void deleteStock (@PathVariable Long id) {
        stockService.deleteStock(id);
    }

    @PutMapping("/{id}")
    public Stock updateStock (@RequestBody Stock stock, @PathVariable Long id){
        return stockService.updateStock(stock,id);
    }
}
