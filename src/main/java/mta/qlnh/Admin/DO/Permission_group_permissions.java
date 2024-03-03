package mta.qlnh.Admin.DO;


import jakarta.persistence.*;
import mta.qlnh.Admin.DTO.CompositeKey;


@Entity
@Table(name="permission_group_permissions")
@IdClass(CompositeKey.class)
public class Permission_group_permissions {
    @Id
    @Column(name = "permission_group_id")
    private int permission_group_id;

    public Permission_group_permissions(int permission_group_id, int permission_id) {
        this.permission_group_id = permission_group_id;
        this.permission_id = permission_id;
    }

    public int getPermission_group_id() {
        return permission_group_id;
    }

    public void setPermission_group_id(int permission_group_id) {
        this.permission_group_id = permission_group_id;
    }

    public int getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(int permission_id) {
        this.permission_id = permission_id;
    }

    public Permission_group_permissions() {
    }
    @Id
    @Column(name = "permission_id")
    private int permission_id;
}
