package mta.qlnh.Admin.Service;

import mta.qlnh.Admin.DO.Role;
import mta.qlnh.Admin.DO.Users;
import mta.qlnh.Admin.DAO.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/sub/api/admin/users/")
public class UsersAPI {
    @Autowired
    private UsersService service;
    @Autowired
    private PasswordEncoder passwordEncoder;
//    @CrossOrigin
    @GetMapping("/")
    public List<Users> getAll()
    {
        List<Users> listusers =service.listAll();
        return listusers;
    }
    @GetMapping("/{id}")
    public  ResponseEntity getById(@PathVariable int id) {
        try {
            Optional<Users> usr = service.getUserById(id);
            if (usr.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            return ResponseEntity.status(200).body(usr.stream().toList().get(0));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }

    }

    @PostMapping("/add")
    public ResponseEntity addusers(@RequestBody Users users)
    {
        try
        {
            boolean check = service.checkUserExist(users.getUsername());
            if (check == true){
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("User exist");
            }
            users.setName(users.getFirstname() + users.getLastname());
            users.setPassword(passwordEncoder.encode(users.getPassword()));
            users.setRole(Role.ADMIN);
            users.setStatus("ACTIVATED");
            users.setGroupid(users.getGroup_id());
            service.save(users);
            return ResponseEntity.status(HttpStatus.OK).body("Add user successfully");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteuser(@PathVariable int id)
    {
        try {
            Optional<Users> usr = service.getUserById(id);
            if (!usr.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
            else{
                usr.get().setStatus("DELETED");
                service.save(usr.get());
            }
//            service.delete(id);

            return ResponseEntity.status(200).body("Delete user successfully");
        }catch (Exception e) {
            return ResponseEntity.status(304).body("Not modified");
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateusers(@RequestBody Users user)
    {
        try {
            Optional <Users> usr = service.getUserByUsername(user.getUsername());
            if (usr.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
            Users userUpdated = service.getByUsername(user.getUsername());
            userUpdated.setFirstname(user.getFirstname());
            userUpdated.setLastname(user.getLastname());
            userUpdated.setName(user.getFirstname()+user.getLastname());
            userUpdated.setEmail(user.getEmail());
            userUpdated.setPhone(user.getPhone());
            userUpdated.setPosition(user.getPosition());
            userUpdated.setSalary(user.getSalary());
            userUpdated.setPassword(passwordEncoder.encode(user.getPassword()));
            if(user.getStatus()!= null){userUpdated.setStatus(user.getStatus());}
            if(user.getRole()!= null){userUpdated.setStatus(user.getRole().name());}
            userUpdated.setGroup_id(user.getGroup_id());
            service.save(userUpdated);
            return ResponseEntity.status(200).body("Update user successfully");
        }catch (Exception e) {
            return ResponseEntity.status(304).body("Not modified");
        }
    }
}
