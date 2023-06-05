package com.ecommerce.userservice.services;

import com.ecommerce.userservice.models.Deliveryman;
import com.ecommerce.userservice.repositories.DeliverymanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliverymanService {

    private final DeliverymanRepository deliverymanRepository;

    private final Path rootLocation = Paths.get("upload");


    public List<Deliveryman> getAll() {
        return deliverymanRepository.findAll();
    }

    public Deliveryman getOneDeliveryman(Long id) {
        return deliverymanRepository.findById(id).orElse(null);
    }

    public Deliveryman createDeliveryman (Deliveryman deliveryman) {
        return deliverymanRepository.save(deliveryman);
    }

    public void deleteDeliveryman(Long id) {
        deliverymanRepository.deleteById(id);
    }

    public Deliveryman updateDeliveryman (Deliveryman updateDeliveryman, Long id){
        Deliveryman deliveryman = deliverymanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found with id: " + id));


        deliveryman.setUsername(updateDeliveryman.getUsername());
        deliveryman.setPassword(updateDeliveryman.getPassword());
        deliveryman.setPhone(updateDeliveryman.getPhone());
        deliveryman.setAvailable(updateDeliveryman.isAvailable());
        deliveryman.setVehicleRef(updateDeliveryman.getVehicleRef());
        return deliverymanRepository.save(deliveryman);

    }

}
