package mta.qlnh.Admin.Service;

import mta.qlnh.Admin.DO.Permissions;
import mta.qlnh.Admin.DTO.Permissions_cli;
import mta.qlnh.Admin.DAO.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/sub/api/admin/permissions/")
public class PermissionsAPI {
    @Autowired
    private PermissionsService service;

//    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity  getAll(@RequestParam(name="name", required = false)String n,
                                  @RequestParam(name="description", required = false)String d) {
        try {
            if(d==null) d="";
            if(n==null) n="";

            List<Permissions> listPermissions = service.Filter(n,d);
            List<Permissions_cli> list = new ArrayList();
            for (Permissions obj: listPermissions){
                Permissions_cli p_cli = new Permissions_cli(obj.getId(), obj.getName(), obj.getDescription());
                list.add(p_cli);
            }
            return ResponseEntity.status(200).body(list);
        }
        catch(Exception e){
            return ResponseEntity.status(404).body("Not found !");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable int id){
        try {
            Permissions result = service.getPermissionsById(id);
            Permissions_cli cli = new Permissions_cli(result.getId(), result.getName(), result.getDescription());
            return ResponseEntity.status(200).body(cli);
        }
        catch(Exception e){
            return ResponseEntity.status(404).body("Not found !");
        }
    }


    @PostMapping("/add")
    public ResponseEntity addPermissions(@RequestBody Permissions_cli Permissions_cli) throws Exception {
        try {
            List<Permissions> check = service.getPermissionsByName(Permissions_cli.getName());
            if(!check.isEmpty()){
                return ResponseEntity.status(304).body("Permission existed !");
            }
            Permissions permissions = new Permissions(Permissions_cli.getName(), Permissions_cli.getDescription());
            service.save(permissions);
            return ResponseEntity.status(200).body("Add Permission successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(304).body("Permission existed!");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePermissions(@PathVariable int id) {
        try {
            service.delete(id);
            return ResponseEntity.status(200).body("Delete Permission Information successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Not found!");
        }
    }
    @PutMapping("/update")
    public ResponseEntity updatePermissions(@RequestBody Permissions_cli Permissions_cli)
    {
        try {
            Permissions updatePermissions = service.getPermissionsById(Permissions_cli.getId());
            updatePermissions.setName(Permissions_cli.getName());
            updatePermissions.setDescription(Permissions_cli.getDescription());
            service.save(updatePermissions);
            return ResponseEntity.status(200).body("Update Permission Information successfully!");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(304).body("Not found!");
        }
    }

}
