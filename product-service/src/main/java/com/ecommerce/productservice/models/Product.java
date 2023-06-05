package com.ecommerce.productservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String price;
    private String description;
    private String qte;
    private String color;
    private String brand  ;
    private String state  ;
    private String location  ;
    private boolean available  ;
    private LocalDateTime date;
    private String image;


    @ManyToOne
    @JoinColumn(name="subcategory_id")
    private Subcategory subcategory;

    private Long providerId;

    @ElementCollection
    private Collection<Long> ordersIds;

    private Long stockId;

    @ElementCollection
    private Collection<Long> commandIds;

    private Long cartId;


}


