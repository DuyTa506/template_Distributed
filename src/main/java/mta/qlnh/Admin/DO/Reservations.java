package mta.qlnh.Admin.DO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.web.reactive.function.BodyInserter;

import java.util.Set;

@Entity
public class Reservations  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int num_people;
    private String status;
    private int customers_id;
    @OneToMany(mappedBy = "reservations")
    @JsonManagedReference(value = "reser_inv")
    private Set<Invoices> invoices;

    private int curr_inv_id;

    public int getCurr_inv_id() {
        return curr_inv_id;
    }

    public void setCurr_inv_id(int curr_inv_id) {
        this.curr_inv_id = curr_inv_id;
    }

    public Reservations() {
    }

    public Reservations(int id, String name, int num_people, String status, int customers_id, Set<Invoices> invoices) {
        this.id = id;
        this.name = name;
        this.num_people = num_people;
        this.status = status;
        this.customers_id = customers_id;
        this.invoices = invoices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum_people() {
        return num_people;
    }

    public void setNum_people(int num_people) {
        this.num_people = num_people;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCustomers_id() {
        return customers_id;
    }

    public void setCustomers_id(int customers_id) {
        this.customers_id = customers_id;
    }

    public Set<Invoices> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoices> invoices) {
        this.invoices = invoices;
    }
}
