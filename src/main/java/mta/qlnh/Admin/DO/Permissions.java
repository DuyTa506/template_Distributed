package mta.qlnh.Admin.DO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.*;
@Entity
@Table(name="permissions")
public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String name;
    private String description;


    public Permissions(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Permissions() {
    }

    public Permissions(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @ManyToMany(mappedBy = "grPermissions")
    @JsonBackReference
    private List<Permission_groups> PerGroups;

    public List<Permission_groups> getPerGroups() {
        return PerGroups;
    }

    public void setPerGroups(List<Permission_groups> perGroups) {
        PerGroups = perGroups;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }


}
