package com.ecommerce.productservice.models;

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
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;


    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;



    @OneToMany (mappedBy = "subcategory",cascade = CascadeType.REMOVE)
    private Collection<Product> products;


    @OneToMany(mappedBy = "subcategory",cascade = CascadeType.REMOVE)
    private Collection<Vehicle> Vehicules;


    @OneToMany(mappedBy = "subcategory",cascade = CascadeType.REMOVE)
    private Collection<Property> Immobiliers;


}

