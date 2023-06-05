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
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long qtetotal;
    private String date_cmd;
    private String description_cmd ;
    private String duree_cmd;
    private String etat_cmd;

    private Long clientId;
    @ElementCollection
    private Collection<Long> productIdsList;
    @ElementCollection
    private Collection<Long> propertyIdsList;
    @ElementCollection
    private Collection<Long> vehicleIdsList;
}
