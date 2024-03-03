package mta.qlnh.Client.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "dishtypes_id",nullable = false, referencedColumnName = "dishtypes_id")
    @JsonBackReference
    private Dishtypes dishtypes;

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

    public Dishtypes getDishtypes() {
        return dishtypes;
    }

    public void setDishtypes(Dishtypes dishtypes) {
        this.dishtypes = dishtypes;
    }
}
