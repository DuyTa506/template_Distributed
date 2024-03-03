package mta.qlnh.Admin.Controller;


import jakarta.persistence.criteria.Order;
import jakarta.servlet.http.HttpServletRequest;
import mta.qlnh.Admin.DO.*;
import mta.qlnh.Admin.DTO.*;
import mta.qlnh.Admin.DAO.*;
import mta.qlnh.Admin.DataGateway.DishesGW;
import mta.qlnh.Admin.DataGateway.OrderGW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/admin/order/")
public class OrderController {

    private OrderGW gateway = new OrderGW();
//    @CrossOrigin
    @PostMapping("/newOrder")
    public ResponseEntity newOrder(@RequestBody NewOrderRequest orderRequest, HttpServletRequest request)
    {

        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        try {
            int result = gateway.newOrder(orderRequest, authHeader);
            if (result ==200)
                return ResponseEntity.status(200).body(result);
            else
                return ResponseEntity.status(404).body("Not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

//    @CrossOrigin
    @PostMapping("/addOrder")
    public ResponseEntity order(@RequestBody OrderRequest orderRequest) {

        try {
            OrderRequest result = gateway.order(orderRequest);
            if (result != null)
                return ResponseEntity.status(200).body(result);
            else
                return ResponseEntity.status(404).body("Not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }

    }

    @PostMapping("/cancelOrder")
    public ResponseEntity cancelOrder(@RequestBody Request1Param req){
        try {
            int result = gateway.cancelOrder(req);
            if (result ==200)
                return ResponseEntity.status(200).body(result);
            else if (result ==304) {
                return ResponseEntity.status(304).body("No modify");
            }
            else return ResponseEntity.status(404).body("Not found");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

//    @CrossOrigin
    @GetMapping("/reservations/{id}")
    public ResponseEntity getOrderByReservation(@PathVariable int id){
        try {
            InvoicesDTO result = gateway.getOrderByReservation(id);
            if (result != null)
                return ResponseEntity.status(200).body(result);

            else return ResponseEntity.status(404).body("Not found");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

//    @CrossOrigin
    @PostMapping("/updateOrderDetail")
    public ResponseEntity updateOrder(@RequestBody Invoices_detailsDTO invoicesDetailsDTO)
    {
        try {
            int result = gateway.updateOrder(invoicesDetailsDTO);
            if (result ==200)
                return ResponseEntity.status(200).body(result);
            else if (result ==304) {
                return ResponseEntity.status(304).body("No modify");
            }
            else return ResponseEntity.status(404).body("Not found");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

}
