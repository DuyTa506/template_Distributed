package mta.qlnh.Admin.Service;

import mta.qlnh.Admin.DO.Permission_groups;
import mta.qlnh.Admin.DO.Permissions;
import mta.qlnh.Admin.DTO.*;
import mta.qlnh.Admin.DAO.Permission_groupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/sub/api/admin/permissionGroups/")
public class Permission_groupsAPI {
    @Autowired
    private Permission_groupsService service;

//    @CrossOrigin

//    @GetMapping("/")
//    public ResponseEntity  getAll() {
//
//        try {
//            List<Permission_groups> result = service.listAll();
//            List<Permission_groups_cli> pg_cli = new ArrayList<>();
//            for (Permission_groups item: result){
//                pg_cli.add( new Permission_groups_cli(item));
//            }
//            return ResponseEntity.status(200).body((ArrayList<Permission_groups_cli> ) pg_cli);
//        }
//        catch(Exception e){
//            return ResponseEntity.status(404).body("Not found !");
//        }
//
//    }
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
            List<Permission_groups> result = service.Filter(n,d,a,s);
            List<Permission_groups_cli> pg_cli = new ArrayList<>();
            for (Permission_groups item: result){
                pg_cli.add( new Permission_groups_cli(item));
            }
            return ResponseEntity.status(200).body((ArrayList<Permission_groups_cli>) pg_cli);
        }
        catch(Exception e){
            return ResponseEntity.status(404).body("Not found !");
        }

    }


    @GetMapping("/{id}")
    public ResponseEntity GetById (@PathVariable int id) {

        try {
            Permission_groups result = service.getPermission_groupsById(id);
            return ResponseEntity.status(200).body(new Permission_groups_cli(result));
        }
        catch(Exception e){
            return ResponseEntity.status(404).body("Not found !");
        }

    }

    @GetMapping("/getPermissions/{id}")
    public ResponseEntity GetPermissionsOfGroup(@PathVariable int id){
        try {
            List<Permissions> result = service.getPermissionOfGroup(id);
            List<Permissions_cli> list = new ArrayList ();
            Permissions_cli p_cli =new Permissions_cli();
            for (Permissions obj: result){
                p_cli = new Permissions_cli(obj.getId(), obj.getName(), obj.getDescription());
                list.add(p_cli);
            }
            return ResponseEntity.status(200).body((ArrayList<Permissions_cli>) list);
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
