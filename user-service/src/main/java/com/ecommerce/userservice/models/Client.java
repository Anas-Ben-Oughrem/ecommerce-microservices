package com.ecommerce.userservice.models;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client extends User {
    private String address;
    private String city;
    private String image;
    @ElementCollection
    private List<Integer> ordersIdList;

}
