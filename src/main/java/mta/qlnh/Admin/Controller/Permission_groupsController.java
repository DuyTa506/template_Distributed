package mta.qlnh.Admin.Controller;

import mta.qlnh.Admin.DO.Permission_groups;
import mta.qlnh.Admin.DO.Permissions;
import mta.qlnh.Admin.DTO.*;
import mta.qlnh.Admin.DAO.Permission_groupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mta.qlnh.Admin.DataGateway.Permission_groupsGW;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/admin/permissionGroups/")
public class Permission_groupsController {
    @Autowired
    private Permission_groupsService service;
    private Permission_groupsGW gateway = new Permission_groupsGW();

    @GetMapping("/")
    public ResponseEntity  getAll(@RequestParam(name="name", required = false)String n,
                                  @RequestParam(name="description", required = false)String d,
                                  @RequestParam(name="action", required = false)String a,
                                  @RequestParam(name="status", required = false)String s) {

        try {
            if(d==null) d="";
            if(n==null) n="";
            if(s==null) s="";
            if(a==null) a="";
            List<Permission_groups_cli> result = gateway.getAll(n, d, a, s);
            return ResponseEntity.status(200).body(result);
        }
        catch(Exception e){
            return ResponseEntity.status(404).body("Not found !");
        }

    }


    @GetMapping("/{id}")
    public ResponseEntity GetById (@PathVariable int id) {

        try {
            Permission_groups_cli result = gateway.getById(id);
            return ResponseEntity.status(200).body(result);
        }
        catch(Exception e){
            return ResponseEntity.status(404).body("Not found !");
        }

    }

    @GetMapping("/getPermissions/{id}")
    public ResponseEntity GetPermissionsOfGroup(@PathVariable int id){
        try {
            List<Permissions_cli> result = gateway.GetPermissionsOfGroup(id);
            return ResponseEntity.status(200).body(result);
        }
        catch(Exception e){
            return ResponseEntity.status(404).body("Not found !");
        }

    }

    @PostMapping("/add")
    public ResponseEntity addPermission_groups(@RequestBody Permission_groups_cli t_cli) throws Exception {
        try {
            List<Permission_groups> check = service.getPermission_GroupsByName(t_cli.getName());
            if(!check.isEmpty()){
                return ResponseEntity.status(304).body("Group existed !");
            }
            t_cli.setStatus("ACTIVATED");
            t_cli.setCreate_at(LocalDateTime.now());
            Permission_groups t = new Permission_groups(t_cli.getName(), t_cli.getDescription(), t_cli.getAction());
            t.setCreate_at(LocalDateTime.now());
            t.setStatus("ACTIVATED");
            service.save(t);
            return ResponseEntity.status(200).body("Add new Group OK!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Error !");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePermission_groups(@PathVariable int id) {
        try {
//            service.delete(id);
            Permission_groups g = service.getPermission_groupsById(id);
            g.setStatus("DELETED");
            service.save(g);
            return ResponseEntity.status(200).body("Delete group successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Not found!");
        }
    }
    @PutMapping("/update")
    public ResponseEntity updatePermission_groups(@RequestBody Permission_groups_cli t_cli)
    {
        try {
            List<Permission_groups> check = service.getPermission_GroupsByName(t_cli.getName());
            if(check.isEmpty()){
                return ResponseEntity.status(404).body("404 not found !");
            }
            Permission_groups updatePermission_groups = service.getPermission_groupsById(t_cli.getId());
            updatePermission_groups.setName(t_cli.getName());
            updatePermission_groups.setDescription(t_cli.getDescription());
            updatePermission_groups.setStatus(t_cli.getStatus());
            service.save(updatePermission_groups);
            return ResponseEntity.status(200).body("Update successfully!");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(500).body("Internal Error!");
        }
    }


}
