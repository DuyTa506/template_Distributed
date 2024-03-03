package mta.qlnh.Client.Controller;

import mta.qlnh.Client.Entity.Customers;
import mta.qlnh.Client.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client/api/customers/")
public class CustomersController {
    @Autowired 
    private CustomerService service;
    @CrossOrigin
    @GetMapping("/")
    public List<Customers> getAll()
    {
        List<Customers> listCustomers =service.listAll();
        return listCustomers;
    }
}
