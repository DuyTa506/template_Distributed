package mta.qlnh.Admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mta.qlnh.Admin.DO.Invoice_details;
import mta.qlnh.Admin.DO.Reservations;

import java.sql.Time;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Invoice_details_K {
    private int id;
    private int invoices_id;
    private DishesDTO dishes;
    private String dishes_name;
    private int dishes_type_id;
    private String dishes_type_name;
    private int quantity;
    private ReservationsDTO reservationsDTO;
    private String reservation_name;
    private int coocked_num;
    private int ordered_num;
    private double price;
    private String status;
    private Time orderdAt;
    private Time receivedAt;
    private Time acceptedAt;
    private String note;
    private String info;

    public Invoice_details_K(Invoice_details i, Reservations r){
        this.id = i.getId();
        this.invoices_id = i.getInvoices().getId();
        this.dishes = new DishesDTO(i.getDishes());
        this.dishes_name = dishes.getName();
        this.dishes_type_id = dishes.getDishtypes().getId();
        this.dishes_type_name = dishes.getDishtypes().getName();
        this.quantity = i.getQuantity();
        this.coocked_num = i.getCoocked_num();
        this.ordered_num = i.getOrdered_num();
        this.price = i.getPrice();
        this.orderdAt = i.getOrderdAt();
        this.receivedAt = i.getReceivedAt();
        this.acceptedAt = i.getAcceptedAt();
        this.note = i.getNote();
        this.info = i.getInfo();
        this.status = i.getStatus();
        this.reservationsDTO = new ReservationsDTO(r);
        this.reservation_name = reservationsDTO.getName();
    }
}
