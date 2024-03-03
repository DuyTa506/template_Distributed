package mta.qlnh.Admin.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewOrderRequest {
    private int reservations_id;
    private int customer_id;
    private int customer_num;
    private int invoice_id;
    private String customer_name;
    private String customer_phone;
}
