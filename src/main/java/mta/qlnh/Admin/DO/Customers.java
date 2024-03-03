package mta.qlnh.Admin.DO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Customers
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phone;
    private String email;
    @OneToMany(mappedBy = "customers")
    @JsonManagedReference(value = "cus_inv")
    private Set<Invoices> invoices;
    public Customers() {
    }
    public Customers(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Invoices> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoices> invoices) {
        this.invoices = invoices;
    }
}
