package com.ecommerce.orderservice.controllers;

import com.ecommerce.orderservice.models.Delivery;
import com.ecommerce.orderservice.services.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Delivery")
@CrossOrigin("*")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Delivery> getAll(){
        return deliveryService.getAll() ;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery createDelivery (@RequestBody Delivery delivery){
        return deliveryService.createDelivery(delivery);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDelivery (@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Delivery updateDelivery (@RequestBody Delivery delivery, @PathVariable Long id) {
        return deliveryService.updateDelivery(delivery, id);
    }
}
