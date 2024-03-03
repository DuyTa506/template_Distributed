package mta.qlnh.Admin.DO;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import java.util.List;

@Entity
@Table(name="permission_groups")
public class Permission_groups{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String name;
    private String description;
    private String action;
    private LocalDateTime create_at;
    private String status;
    public Permission_groups(String name, String description, String action) {
        this.name = name;
        this.description = description;
        this.action = action;
    }

    public Permission_groups(int id, String name, String description, String action, LocalDateTime create_at, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.action = action;
        this.create_at = create_at;
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    private DateTime create_at;



    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "permission_group_permissions",
            joinColumns = @JoinColumn(name = "permission_group_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))

    @JsonManagedReference
    private List<Permissions> grPermissions;

    public List<Permissions> getGrPermissions() {
        return grPermissions;
    }

    public void setGrPermissions(List<Permissions> grPermissions) {
        this.grPermissions = grPermissions;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Permission_groups(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Permission_groups() {
    }
}

