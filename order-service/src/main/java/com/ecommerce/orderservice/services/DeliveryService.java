package com.ecommerce.orderservice.services;

import com.ecommerce.orderservice.models.Delivery;
import com.ecommerce.orderservice.repositories.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public List<Delivery> getAll(){
        return deliveryRepository.findAll() ;    }

    public Delivery createDelivery ( Delivery delivery){
        return deliveryRepository.save(delivery);
    }

    public void deleteDelivery (Long id) {
        deliveryRepository.deleteById(id);
    }

    public Delivery updateDelivery ( Delivery delivery,  Long id){
        Delivery l1 = deliveryRepository.findById(id).orElse(null);
        if (l1 !=null){
            delivery.setId(id);
            return deliveryRepository.saveAndFlush(delivery);
        }
        else{
            throw new RuntimeException("fail");
        }
    }

}
