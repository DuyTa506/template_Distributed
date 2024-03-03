package mta.qlnh.Admin.DO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.sql.Time;

@Entity
public class Invoice_details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "invoice_id",nullable = false )
    @JsonBackReference(value = "detail_inv")
    private Invoices invoices;
    @ManyToOne
    @JoinColumn(name = "dish_id",nullable = false)
    @JsonBackReference(value = "detail_dish")
    private Dishes dishes;
    private int quantity;
    private double price;

    public Time getOrderdAt() {
        return orderdAt;
    }

    public void setOrderdAt(Time orderdAt) {
        this.orderdAt = orderdAt;
    }

    public Time getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(Time receivedAt) {
        this.receivedAt = receivedAt;
    }

    public Time getAcceptedAt() {
        return acceptedAt;
    }

    public void setAcceptedAt(Time acceptedAt) {
        this.acceptedAt = acceptedAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private int reservation_id;
    private int coocked_num;
    private int ordered_num;
    private String status;

    public int getCoocked_num() {
        return coocked_num;
    }

    public void setCoocked_num(int coocked_num) {
        this.coocked_num = coocked_num;
    }

    public int getOrdered_num() {
        return ordered_num;
    }

    public void setOrdered_num(int ordered_num) {
        this.ordered_num = ordered_num;
    }

    private Time orderdAt;
    private Time receivedAt;
    private Time acceptedAt;
    private String note;
    private String info;

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public Invoice_details() {

    }

    public Invoice_details(int id, Invoices invoices, Dishes dishes, int quantity, double price) {
        this.id = id;
        this.invoices = invoices;
        this.dishes = dishes;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Invoices getInvoices() {
        return invoices;
    }

    public void setInvoices(Invoices invoices) {
        this.invoices = invoices;
    }

    public Dishes getDishes() {
        return dishes;
    }

    public void setDishes(Dishes dishes) {
        this.dishes = dishes;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
