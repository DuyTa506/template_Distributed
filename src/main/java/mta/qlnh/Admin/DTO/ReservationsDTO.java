package mta.qlnh.Admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mta.qlnh.Admin.DO.Reservations;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationsDTO {

    private int id;
    private String name;


    private int num_people;
    private String status;
    private int customers_id;
    private int curr_inv_id;
    private InvoicesDTO current_inv;
    private CustomersDTO customersDTO;
    public ReservationsDTO(Reservations res) {
        this.id = res.getId();
        this.name = res.getName();
        this.num_people = res.getNum_people();
        this.status = res.getStatus();
        this.customers_id = res.getCustomers_id();
        this.curr_inv_id = res.getCurr_inv_id();
    }

}
