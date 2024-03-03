package mta.qlnh.Admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mta.qlnh.Admin.DO.Dishtypes;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishTypesDTO {
    private int id;
    private String name;
    public DishTypesDTO (Dishtypes d){
        this.id = d.getId();
        this.name = d.getName();
    }
}
