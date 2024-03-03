package mta.qlnh.Admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mta.qlnh.Admin.DO.Invoice_details;
import mta.qlnh.Admin.DO.Invoices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoicesDTO {

    private int id;

    private CustomersDTO customers;

    private int user_id;

    private ReservationsDTO reservations;
    private Date date;
    private double total_amount;
    private String payment;
    private Date getout;


    public Date getGetout() {
        return getout;
    }

    public void setGetout(Date getout) {
        this.getout = getout;
    }
    private List<Invoices_detailsDTO> invoice_details;

    public InvoicesDTO(Invoices inv){
        this.id = inv.getId();
        this.date = inv.getDate();
        this.reservations = new ReservationsDTO(inv.getReservations());
        this.user_id = inv.getUsers().getId();
        this.customers = new CustomersDTO(inv.getCustomers());
        this.total_amount = inv.getTotal_amount();
        this.payment = inv.getPayment();
        this.getout = inv.getGetout();
        List<Invoice_details> inv_dts = new ArrayList(inv.getInvoice_details());
        invoice_details = new ArrayList<>();
        for (Invoice_details item: inv_dts
             ) {
            Invoices_detailsDTO i = new Invoices_detailsDTO(item);
            invoice_details.add(i);
        }
    }

}
