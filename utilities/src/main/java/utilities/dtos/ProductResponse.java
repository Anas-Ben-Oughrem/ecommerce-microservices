package utilities.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private String price;
    private String description;
    private String color;
    private String brand  ;
    private String state  ;
    private String location  ;
    private boolean available  ;
}
