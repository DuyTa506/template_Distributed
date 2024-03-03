package mta.qlnh.Client.Controller;

import mta.qlnh.Client.Entity.Users;
import mta.qlnh.Client.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client/api/users/")
public class UsersController {
    @Autowired
    private UsersService service;
    @CrossOrigin
    @GetMapping("/")
    public List<Users> getAll()
    {
        List<Users> listusers =service.listAll();
        return listusers;
    }
}
