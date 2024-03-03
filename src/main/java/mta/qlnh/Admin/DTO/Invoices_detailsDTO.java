package mta.qlnh.Admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mta.qlnh.Admin.DO.Invoice_details;

import java.sql.Time;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Invoices_detailsDTO {
    private int id;
    private int invoices_id;
    private DishesDTO dishes;
    private int quantity;
    private double price;
    private String status;
    private Time orderdAt;
    private Time receivedAt;
    private Time acceptedAt;
    private String note;
    private String info;

    public Invoices_detailsDTO(Invoice_details i){
        this.id = i.getId();
        this.invoices_id = i.getInvoices().getId();
        this.dishes = new DishesDTO(i.getDishes());
        this.quantity = i.getQuantity();
        this.price = i.getPrice();
        this.orderdAt = i.getOrderdAt();
        this.receivedAt = i.getReceivedAt();
        this.acceptedAt = i.getAcceptedAt();
        this.note = i.getNote();
        this.info = i.getInfo();
        this.status = i.getStatus();

    }
}
