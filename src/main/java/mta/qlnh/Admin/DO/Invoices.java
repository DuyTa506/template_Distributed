package mta.qlnh.Admin.DO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class Invoices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference(value = "cus_inv")
    private Customers customers;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user_inv")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "reservations_id")
    @JsonBackReference(value = "reser_inv")
    private Reservations reservations;
    private Date date;
    private Date getout;
    private double total_amount;

    public Date getGetout() {
        return getout;
    }

    public void setGetout(Date getout) {
        this.getout = getout;
    }

    @OneToMany(mappedBy = "invoices",cascade = CascadeType.ALL)
    @JsonBackReference(value = "detail_inv")
    private Set<Invoice_details> invoice_details;
    private String payment;

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Invoices() {
    }

    public Invoices(int id, Customers customers, Users users, Reservations reservations, Date date, double total_amount, Set<Invoice_details> invoice_details) {
        this.id = id;
        this.customers = customers;
        this.users = users;
        this.reservations = reservations;
        this.date = date;
        this.total_amount = total_amount;
        this.invoice_details = invoice_details;
    }

    public double calTotalPrice(){
        total_amount = 0;
        for (Invoice_details item: invoice_details
             ) {
            total_amount += item.getPrice();
        }
        return total_amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Reservations getReservations() {
        return reservations;
    }

    public void setReservations(Reservations reservations) {
        this.reservations = reservations;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public Set<Invoice_details> getInvoice_details() {
        return invoice_details;
    }

    public void setInvoice_details(Set<Invoice_details> invoice_details) {
        this.invoice_details = invoice_details;
    }
}

