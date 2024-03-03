package mta.qlnh.Client.Controller;

import mta.qlnh.Client.Entity.Dishes;
//import mta.qlnh.Admin.DO.Dishtypes;
import mta.qlnh.Client.Service.DishesService;
//import mta.qlnh.Admin.DAO.DishtypesService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/client/api/dishes/")
public class DishesController {
    @Autowired
    private DishesService service;

    @CrossOrigin
    @GetMapping("/")
    public List<Dishes> getAll() {
        List<Dishes> listDishes = service.listAll();
        return listDishes;
    }
}
