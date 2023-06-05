package com.ecommerce.userservice.models;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Provider extends User{

    private String matricule;
    private String service;
    private String company;


    @ElementCollection
    private List<Long> productIds;
}
