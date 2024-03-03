package mta.qlnh.Admin.DO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Dishtypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "dishtypes",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonBackReference (value = "dish_type")
    private Set<Dishes> dishes;

    public Dishtypes() {
    }

    public Dishtypes(int id, String name ) {
        this.id = id;
        this.name = name;

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

    public Set<Dishes> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dishes> dishes) {
        this.dishes = dishes;
    }

}
