package mta.qlnh.Client.Controller;

import mta.qlnh.Client.Entity.Dishtypes;
import mta.qlnh.Client.Service.DishtypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client/api/dishtypes/")
public class DishtypesController {
    @Autowired
    private DishtypesService service;

    @CrossOrigin
    @GetMapping("/")
    public List<Dishtypes> getAll() {
        List<Dishtypes> listDishtypes = service.listAll();
        return listDishtypes;
    }
}
