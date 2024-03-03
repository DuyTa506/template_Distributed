package mta.qlnh.Admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mta.qlnh.Admin.DO.Customers;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomersDTO {

    private int id;
    private String name;
    private String phone;
    private String email;
    public CustomersDTO (Customers c) {
        this.id = c.getId();
        this.name = c.getName();
        this.phone = c.getPhone();
        this.email = c.getEmail();
    }
}
