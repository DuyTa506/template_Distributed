package mta.qlnh.Admin.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mta.qlnh.Admin.DO.Dishes;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dish4statisticDTO {
    private int id;
    private String name;
    private String image;
    private Double price;
    private String description;
    private DishTypesDTO dishtypes;
    private int number;


    public Dish4statisticDTO (Dishes d){
        this.id = d.getId();
        this.name = d.getName();
        this.dishtypes = new DishTypesDTO(d.getDishtypes());
        this.price = d.getPrice();
        this.description = d.getDescription();
        this.image = d.getImage();
        this.number = 0;
    }
}
