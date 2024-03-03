package mta.qlnh.Admin.Service;

import mta.qlnh.Admin.DTO.CustomersDTO;
import mta.qlnh.Admin.DTO.InvoicesDTO;
import mta.qlnh.Admin.DO.Reservations;
import mta.qlnh.Admin.DTO.ReservationsDTO;
import mta.qlnh.Admin.DAO.CustomersService;
import mta.qlnh.Admin.DAO.InvoicesService;
import mta.qlnh.Admin.DAO.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/sub/api/admin/reservations/")
public class ReservationsAPI {
    @Autowired
    private ReservationsService service;
    @Autowired
    private CustomersService customersService;
    @Autowired
    private InvoicesService invoicesService;

//    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity  getAll() {
        try{
            List<Reservations> listReservations = service.listAll();
            List<ReservationsDTO> result = new ArrayList<>();
            for (Reservations item: listReservations
            ) {
                ReservationsDTO item_dto = new ReservationsDTO(item);
                if(item_dto.getCustomers_id()!= 0)
                    item_dto.setCustomersDTO(new CustomersDTO(customersService.getCustomersById(item_dto.getCustomers_id())));
                if(item_dto.getCurr_inv_id()!= 0)
                    item_dto.setCurrent_inv(new InvoicesDTO(invoicesService.getInvoicesById(item.getCurr_inv_id())));
                result.add(item_dto);
            }
            return ResponseEntity.status(200).body(result);
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }

    }


    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable int id){
        Reservations x = service.getReservationsById(id);
        if (x == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
        return ResponseEntity.status(200).body(x);
    }


    @PostMapping("/add")
    public ResponseEntity addReservations(@RequestBody Reservations reservations) throws Exception {
        try {
            reservations.setCustomers_id(0);
            reservations.setStatus("0");
            service.save(reservations);
            return ResponseEntity.status(200).body("Add successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Error!");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteReservations(@PathVariable int id) {
        try {
            service.delete(id);
            return ResponseEntity.status(200).body("Xóa bàn thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(304).body("Xóa bàn không thành công!");
        }
    }
    @PutMapping("/update")
    public ResponseEntity updateReservations(@RequestBody Reservations reservations)
    {
        try {
            Reservations updatereservations = service.getReservationsById(reservations.getId());
            updatereservations.setName(reservations.getName());
            updatereservations.setNum_people(reservations.getNum_people());
            updatereservations.setStatus(reservations.getStatus());
            updatereservations.setCustomers_id(reservations.getCustomers_id());
            service.save(updatereservations);
            return ResponseEntity.status(200).body("Cập nhật bàn thành công!");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(304).body("Cập nhật bàn không thành công!");
        }
    }

}
