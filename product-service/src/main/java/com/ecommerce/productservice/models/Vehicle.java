package com.ecommerce.productservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre ;
    private String  model;
    private String marque;
    private String price;
    private String lieu;
    private String description;
    private String type;
    private String carosserie;
    private String carrburant;
    private String boite;
    private String etat;
    private String image  ;

    private Long providerId;

    @ManyToOne
    @JoinColumn(name = "Subcategory_id")
    private Subcategory subcategory;

    private Long stockId;

    @ElementCollection
    private Collection<Long> orderIds;

    private Long cartId;


}



