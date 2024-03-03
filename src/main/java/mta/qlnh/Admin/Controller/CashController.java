package mta.qlnh.Admin.Controller;

import mta.qlnh.Admin.DO.Customers;
import mta.qlnh.Admin.DO.Invoices;
import mta.qlnh.Admin.DO.Reservations;
import mta.qlnh.Admin.DO.Users;
import mta.qlnh.Admin.DTO.*;
import mta.qlnh.Admin.DAO.*;
import mta.qlnh.Admin.DataGateway.CashGW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/admin/cash/")
public class CashController {

    private CashGW gateway = new CashGW();

    @GetMapping("/")
    public ResponseEntity getBillWithFilter(@RequestParam(name="startDay", required = false)String startD,
                                            @RequestParam(name="endDay", required = false)String endD,
                                            @RequestParam(name="startHour", required = false)String startH,
                                            @RequestParam(name="endHour", required = false)String endH,
                                            @RequestParam(name="customerName", required = false)String customerName,
                                            @RequestParam(name="customerNum", required = false)String customerNum){
        try{
            List<InvoicesDTO> result = new ArrayList<>();
            result = gateway.getBillWithFilter(startD, endD, startH, endH, customerName, customerNum);

            if (result != null)
            return ResponseEntity.status(200).body(result);
            else
                return ResponseEntity.status(404).body("Not found");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    // Lấy 1 hóa đơn bất kì (nếu đã được tạo)
    @CrossOrigin
    @GetMapping("{id}")
    public ResponseEntity get1Bill(@PathVariable int id){

        try{
            InvoicesDTO result = new InvoicesDTO();
            result = gateway.get1Bill(id);
            if (result != null)
                return ResponseEntity.status(200).body(result);
            else
                return ResponseEntity.status(404).body("Not found");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    // Tính hóa đơn của cái bàn đang ăn kia =)))
//    @CrossOrigin
    @GetMapping("/GetBill/{id}")
    public ResponseEntity  getBillOfCurrenReservation(@PathVariable int id){
        try{
            InvoicesDTO result = new InvoicesDTO();
            result = gateway.getBillOfCurrenReservation(id);
            if (result != null)
                return ResponseEntity.status(200).body(result);
            else
                return ResponseEntity.status(404).body("Not found");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Internal server error");
        }
    }


    // Khách hàng ra khỏi bàn, đòi tạo hóa đơn, nhưng chưa thanh toán
//    @CrossOrigin
    @PostMapping("/ClearReservation/{id}")
    public ResponseEntity  ClearCurrenReservation(@PathVariable int id){
        try{
            InvoicesDTO result = new InvoicesDTO();
            result = gateway.ClearCurrenReservation(id);
            if (result != null)
                return ResponseEntity.status(200).body(result);
            else
                return ResponseEntity.status(404).body("Not found");
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

//    @CrossOrigin
    @PostMapping("/paid/{id}")
    public ResponseEntity  PaymentConfirm(@PathVariable int id) {

        try {
            InvoicesDTO result = new InvoicesDTO();
            result = gateway.PaymentConfirm(id);
            if (result != null)
                return ResponseEntity.status(200).body(result);
            else
                return ResponseEntity.status(404).body("Not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }

    }


}
