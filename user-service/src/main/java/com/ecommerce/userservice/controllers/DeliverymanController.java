package com.ecommerce.userservice.controllers;

import com.ecommerce.userservice.models.Deliveryman;
import com.ecommerce.userservice.services.DeliverymanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveryman")
@RequiredArgsConstructor
public class DeliverymanController {

    private final DeliverymanService deliverymanService;

    @PostMapping
    public Deliveryman createDeliveryman (Deliveryman deliveryman){
        return deliverymanService.createDeliveryman(deliveryman);
    }

    @GetMapping
    public List<Deliveryman> getAllDeliveryman(){
        return deliverymanService.getAll();
    }

    @GetMapping("/{id}")
    public void getOneDeliveryman( @PathVariable Long id){
        deliverymanService.getOneDeliveryman(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDeliveryman (@PathVariable Long id) {
        deliverymanService.deleteDeliveryman(id);
    }

    @PutMapping("/{id}")
    public Deliveryman updateDeliveryman (@RequestBody Deliveryman deliveryman, @PathVariable Long id) {
        return deliverymanService.updateDeliveryman(deliveryman, id);
    }
}
