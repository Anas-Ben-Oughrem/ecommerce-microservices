package com.ecommerce.userservice.models;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Deliveryman extends User{
    private String phone;
    private boolean available;
    private String vehicleRef;

    @ElementCollection
    private List<Integer> deliveryIdsList;
}
