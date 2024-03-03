package mta.qlnh.Admin.Controller;


import mta.qlnh.Admin.DO.Reservations;
import mta.qlnh.Admin.DTO.ReservationsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import mta.qlnh.Admin.DataGateway.ReservationsGW;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/admin/reservations/")
public class ReservationsController {
    ReservationsGW gateway = new ReservationsGW();

//    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity  getAll() {
        try{

            List<ReservationsDTO> result = gateway.getAll();
            return ResponseEntity.status(200).body(result);
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable int id){
        Reservations x = gateway.getById(id);
        if (x == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
        return ResponseEntity.status(200).body(x);
    }


    @PostMapping("/add")
    public ResponseEntity addReservations(@RequestBody Reservations reservations) throws Exception {
        try {
            int status = gateway.addReservations(reservations);
            if (status == 200)
            {
                return ResponseEntity.status(200).body("Add successfully!");
            }
            else {
                return ResponseEntity.status(304).body("No modify!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Error!");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteReservations(@PathVariable int id) {
        try {
//            service.delete(id);
            int status = gateway.deleteReservations(id);
            if (status == 200)
                return ResponseEntity.status(200).body("Xóa bàn thành công!");
            else
                return ResponseEntity.status(304).body("No modify!");
        } catch (Exception e) {
            return ResponseEntity.status(304).body("Xóa bàn không thành công!");
        }
    }
    @PutMapping("/update")
    public ResponseEntity updateReservations(@RequestBody Reservations reservations)
    {
        try {
            int status = gateway.updateReservations(reservations);
            if (status == 200)
            {
                return ResponseEntity.status(200).body("Cập nhật bàn thành công!");
            }
            return ResponseEntity.status(304).body("Cập nhật bàn không thành công!");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(304).body("Cập nhật bàn không thành công!");
        }
    }

}
