package mta.qlnh.Admin.Controller;

import mta.qlnh.Admin.DO.Permissions;
import mta.qlnh.Admin.DTO.Permissions_cli;
import mta.qlnh.Admin.DAO.PermissionsService;
import mta.qlnh.Admin.DataGateway.PermissionsGW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/admin/permissions/")
public class PermissionsController {

    private PermissionsGW gateway = new PermissionsGW();

//    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity  getAll(@RequestParam(name="name", required = false)String n,
                                  @RequestParam(name="description", required = false)String d) {
        try {
            List<Permissions_cli> result = gateway.getAll(d, n);
            if (result == null)
            {
                return ResponseEntity.status(500).body("Internal error !");
            }
            else return ResponseEntity.status(200).body(result);
        }
        catch(Exception e){
            return ResponseEntity.status(500).body("Internal error !");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable int id){
        try {
//            Permissions result = service.getPermissionsById(id);
            Permissions_cli cli = gateway.getById(id);
            return ResponseEntity.status(200).body(cli);
        }
        catch(Exception e){
            return ResponseEntity.status(404).body("Not found !");
        }
    }


    @PostMapping("/add")
    public ResponseEntity addPermissions(@RequestBody Permissions_cli Permissions_cli) throws Exception {
        try {
            int status = gateway.addPermissions(Permissions_cli);
            if (status == 200)
                return ResponseEntity.status(200).body("Add Permission successfully!");
            if (status == 304)
                return ResponseEntity.status(200).body("Add Permission successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(304).body("Permission existed!");
        }
        return null;
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePermissions(@PathVariable int id) {
        try {
            int status = gateway.deletePermissions(id);
            if(status == 200)
            return ResponseEntity.status(200).body("Delete Permission Information successfully!");
            else
                return ResponseEntity.status(304).body("No modified!");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Not found!");
        }
    }
    @PutMapping("/update")
    public ResponseEntity updatePermissions(@RequestBody Permissions_cli Permissions_cli) {

        try {
            int status = gateway.updatePermissions(Permissions_cli);
            if (status == 200)
                return ResponseEntity.status(200).body("Add Permission successfully!");
            if (status == 304)
                return ResponseEntity.status(200).body("Add Permission successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(304).body("Permission existed!");
        }
        return null;
    }

}
