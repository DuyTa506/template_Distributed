package mta.qlnh.Admin.DTO;
import lombok.Getter;
import lombok.Setter;
import mta.qlnh.Admin.DO.Permission_groups;
import mta.qlnh.Admin.DO.Permissions;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
public class Permission_groups_cli{
    private int id;
    private String name;
    private String description;
    private String action;
    private LocalDateTime create_at;

    public Permission_groups_cli(int id, String name, String description, String action, LocalDateTime create_at, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.action = action;
        this.create_at = create_at;
        this.status = status;
    }
    public Permission_groups_cli(Permission_groups pg){
        this.id = pg.getId();
        this.name = pg.getName();
        this.description = pg.getDescription();
        this.action = pg.getAction();
        this.create_at = pg.getCreate_at();
        this.status = pg.getStatus();
        List<Permissions_cli> pg_cli = new ArrayList<>();
        for (Permissions item: pg.getGrPermissions()){
            pg_cli.add(new Permissions_cli(item));
        }
        this.grPermissions = pg_cli;
    }

    //    private DateTime create_at;
    private String status;


    private List<Permissions_cli> grPermissions;

    public Permission_groups_cli(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Permission_groups_cli() {
    }
}

