package com.ecommerce.stockservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String product;
    private String quantite;

    @ElementCollection
    private Collection<Long> productIds;
    @ElementCollection
    private Collection<Long> VehiculeIds;
    @ElementCollection
    private Collection<Long> ImmobiliersIds;
}
