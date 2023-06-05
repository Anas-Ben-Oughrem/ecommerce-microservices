package com.ecommerce.orderservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId ;
    private String  creationDate;
    private Float total;

    @ElementCollection
    private Collection<Long> ProductIdsList;
    @ElementCollection
    private Collection<Long> PropertyIdsList;
    @ElementCollection
    private Collection<Long> VehicleIdsList;
}
