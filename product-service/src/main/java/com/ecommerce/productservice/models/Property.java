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
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private int roomNumber;
    private String nb_salle;
    private String price;
    private String address;
    private String surface;
    private String date;
    private String parking;
    private String cooling;
    private String heating;
    private String description;
    private String image;

    private Long providerId;

    @ManyToOne
    @JoinColumn(name = "Subcategory_id")
    private Subcategory subcategory;

    private Long stockId;

    @ElementCollection
    private Collection<Long> commandsIds;

    private Long cartId;


}

