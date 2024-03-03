package mta.qlnh.Admin.Controller;

import mta.qlnh.Admin.DO.Invoice_details;
import mta.qlnh.Admin.DO.Reservations;
import mta.qlnh.Admin.DTO.*;
import mta.qlnh.Admin.DAO.Invoice_detailsService;
import mta.qlnh.Admin.DAO.InvoicesService;
import mta.qlnh.Admin.DAO.ReservationsService;
import mta.qlnh.Admin.DataGateway.KitchenGW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/admin/kitchen/")
public class KitchenController {

    private KitchenGW gateway = new KitchenGW();
    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity  getAll() {

        try {
            List<Invoice_details_K> result = gateway.getAll();
            if (result != null)
                return ResponseEntity.status(200).body(result);
            else
                return ResponseEntity.status(404).body("Not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
}

    @CrossOrigin
    @GetMapping("/byId/")
    public ResponseEntity  getAll(@RequestParam(name="dishtype_id", required = false) int t) {

        try {
            List< Invoice_details_K> result = gateway.getByID(t);

            return ResponseEntity.status(200).body(result);
        }
        catch(Exception e){
            return ResponseEntity.status(404).body("Not found !");
        }
    }

    @CrossOrigin
    @PostMapping("/accepted/{order_detail_id}")
    public ResponseEntity acceptedOrder(@PathVariable int order_detail_id){
        try {
            Invoices_detailsDTO result = new Invoices_detailsDTO();
            return ResponseEntity.status(200).body(result);
        }
        catch (Exception e){
            return ResponseEntity.status(404).body("Not found");
        }
    }

    @CrossOrigin
    @PostMapping("/cooked")
    public ResponseEntity cooked(@RequestBody OrderObjectRequest obj){
        try {
            Invoices_detailsDTO invoiceDetails = gateway.cooked(obj);
            if (invoiceDetails != null)
                return ResponseEntity.status(200).body(invoiceDetails);
            else
                return ResponseEntity.status(404).body("Not found");
        }
        catch (Exception e){
            return ResponseEntity.status(304).body("Không tìm thấy hoặc số lượng lớn hơn số được order!");
        }
    }

    @CrossOrigin
    @PostMapping("/ordered")
    public ResponseEntity ordered(@RequestBody OrderObjectRequest obj){
        try {
            Invoices_detailsDTO invoiceDetails = gateway.ordered(obj);
            if (invoiceDetails != null)
                return ResponseEntity.status(200).body(invoiceDetails);
            else
                return ResponseEntity.status(404).body("Not found");
        }
        catch (Exception e){
            return ResponseEntity.status(304).body("Không tìm thấy hoặc số lượng lớn hơn số được order!");
        }
    }


}
