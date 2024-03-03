package mta.qlnh.Admin.Controller;

import mta.qlnh.Admin.DO.Role;
import mta.qlnh.Admin.DO.Users;
import mta.qlnh.Admin.DAO.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import mta.qlnh.Admin.DataGateway.UsersGW;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/admin/users/")
public class UsersController {
    @Autowired
    private UsersService service;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private UsersGW gateway = new UsersGW();
//    @CrossOrigin
    @GetMapping("/")
    public List<Users> getAll()
    {
        List<Users> listusers =gateway.getAll();
        return listusers;
    }
    @GetMapping("/{id}")
    public  ResponseEntity getById(@PathVariable int id) throws Exception{
        try {
            Users usr = gateway.getById(id);
            if (usr == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            return ResponseEntity.status(200).body(usr);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }

    }

    @PostMapping("/add")
    public ResponseEntity addusers(@RequestBody Users users) throws Exception
    {
        try {
            int status = gateway.addusers(users);
            if (status == 304) {
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("User exist");
            }
            if (status == 200) {
                return ResponseEntity.status(HttpStatus.OK).body("Add user successfully");
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteuser(@PathVariable int id)
    {
        try {
            int status = gateway.deleteuser(id);
            if (status == 200)
            return ResponseEntity.status(200).body("Delete user successfully");
            else
                return ResponseEntity.status(304).body("Not modified");
        }catch (Exception e) {
            return ResponseEntity.status(304).body("Not modified");
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateusers(@RequestBody Users user)
    {
        try {
            int status = gateway.updateUsers(user);
            if (status == 200)
                return ResponseEntity.status(200).body("Delete user successfully");
            else
                return ResponseEntity.status(304).body("Not modified");
        }catch (Exception e) {
            return ResponseEntity.status(304).body("Not modified");
        }
    }
}
