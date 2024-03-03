package mta.qlnh.Admin.DO;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "dishes")
public class Dishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String image;
    private Double price;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dishtypes_id")
//    @JsonManagedReference (value = "dish_type")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = false)
    private Dishtypes dishtypes;

    @OneToMany(mappedBy = "dishes",cascade = CascadeType.ALL)
    @JsonBackReference(value = "detail_dish")
    private Set<Invoice_details> invoice_details;

    public Dishes() {
    }

    public Dishes(int id, String name, String image, Double price, String description, Dishtypes dishtypes) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.dishtypes = dishtypes;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @JsonProperty("dishtypes")
    public Dishtypes getDishtypes() {
        return dishtypes;
    }

    public void setDishtypes(Dishtypes dishtypes) {
        this.dishtypes = dishtypes;
    }

    public Set<Invoice_details> getInvoice_details() {
        return invoice_details;
    }

    public void setInvoice_details(Set<Invoice_details> invoice_details) {
        this.invoice_details = invoice_details;
    }
}
