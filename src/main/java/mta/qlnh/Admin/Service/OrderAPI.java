package mta.qlnh.Admin.Service;


import jakarta.servlet.http.HttpServletRequest;
import mta.qlnh.Admin.DO.*;
import mta.qlnh.Admin.DTO.*;
import mta.qlnh.Admin.DAO.*;
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
@RequestMapping("/sub/api/admin/order/")
public class OrderAPI {
    @Autowired
    private TokenService service;
    @Autowired
    private ReservationsService reservationsService;
    @Autowired
    private CustomersService customersService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private InvoicesService invoicesService;

    @Autowired
    private Invoice_detailsService invoice_detailsService;
    @Autowired
    private DishesService dishesService;
//    @CrossOrigin
    @PostMapping("/newOrder")
    public ResponseEntity logout(@RequestBody NewOrderRequest orderRequest, HttpServletRequest request)
    {
        try {
            final String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
            String jwt = authHeader.substring(7);
            int uid = service.getUserId(jwt);
            Users user = usersService.getUserById(uid).get();
            // get user
            Invoices invoice = new Invoices();
            invoice.setUsers(user);
            // new customer
            Customers customer = new Customers();
            customer.setPhone(orderRequest.getCustomer_phone());
            customer.setName(orderRequest.getCustomer_name());
            customersService.save(customer);
            invoice.setCustomers(customer);
            invoice.setPayment("PENDING");
            invoice.setGetout(null);
            Reservations reservation = reservationsService.getReservationsById(orderRequest.getReservations_id());
            reservation.setNum_people(orderRequest.getCustomer_num());
            reservation.setCustomers_id(customer.getId());
            reservation.setStatus("1");
            reservationsService.save(reservation);
            invoice.setReservations(reservationsService.getReservationsById(orderRequest.getReservations_id()));
            invoice.setDate(new Date());
            invoice.setTotal_amount(0);
            invoicesService.save(invoice);
            reservation.setCurr_inv_id(invoice.getId());
            reservationsService.save(reservation);
            orderRequest.setInvoice_id(invoice.getId());
            orderRequest.setCustomer_id(customer.getId());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error");
        }

        return ResponseEntity.status(HttpStatus.OK).body(orderRequest);
    }

//    @CrossOrigin
    @PostMapping("/addOrder")
    public ResponseEntity order(@RequestBody OrderRequest orderRequest)
    {
        try {
            List<OrderDTO> orders = orderRequest.getOrderDTOList();
            Reservations reservations = reservationsService.getReservationsById(orderRequest.getReservations_id());
            Invoices invoices = invoicesService.getInvoicesById(reservations.getCurr_inv_id());
            for (OrderDTO item: orders
                 ) {
                Invoice_details invoiceDetails = new Invoice_details();
                Dishes dish = dishesService.getDishesById(item.getDish_id());
                invoiceDetails.setInvoices(invoices);
                invoiceDetails.setDishes(dish);
                invoiceDetails.setQuantity(item.getQuantity());
                invoiceDetails.setStatus("ODERED");
                invoiceDetails.setNote(item.getNote());
                invoiceDetails.setInfo("Good");
                invoiceDetails.setPrice(dish.getPrice()* invoiceDetails.getQuantity());
                invoiceDetails.setOrderdAt(Time.valueOf(LocalTime.now()));
                invoiceDetails.setReservation_id(invoices.getReservations().getId());
                invoiceDetails.setCoocked_num(0);
                invoiceDetails.setOrdered_num(0);
                invoice_detailsService.save(invoiceDetails);
                orderRequest.setInvoice_id(invoices.getId());
                item.setId(invoiceDetails.getId());
                orderRequest.setInvoiceDetail_id(invoiceDetails.getId());
                orderRequest.setReservations_id(reservations.getId());
            }
            invoices = invoicesService.getInvoicesById(orderRequest.getInvoice_id());
            invoices.setTotal_amount(invoices.calTotalPrice());
            invoicesService.save(invoices);
            return ResponseEntity.status(HttpStatus.OK).body(orderRequest);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error");
        }
    }

    @PostMapping("/cancelOrder")
    public ResponseEntity cancelOrder(@RequestBody Request1Param req){
        try {
            Reservations reservations = reservationsService.getReservationsById(req.getReservation_id());
            if (reservations.getStatus() == "0"){
                return ResponseEntity.status(304).body("No customer");
            }
            else {

                Invoices curr_inv = invoicesService.getInvoicesById(reservations.getCurr_inv_id());
                Optional<Invoice_details> checked = invoice_detailsService.getByInvoiceIdAndStatus(curr_inv, "CE");
                if(checked.isPresent()){
                    return ResponseEntity.status(304).body("The dishes is coocked");
                }
                invoicesService.delete(reservations.getCurr_inv_id());
                reservations.setStatus("0");
                reservations.setCurr_inv_id(0);
                reservations.setCustomers_id(0);
                reservationsService.save(reservations);
                return ResponseEntity.status(200).body("Cancel order successfully");
            }
        }
        catch (Exception e){
            return ResponseEntity.status(304).body("No modify");
        }
    }

//    @CrossOrigin
    @GetMapping("/reservations/{id}")
    public ResponseEntity getOrderByReservation(@PathVariable int id){
        try{
            Reservations reservations = reservationsService.getReservationsById(id);
            if (reservations == null || reservations.getCustomers_id() == 0){
                return ResponseEntity.status(404).body("Not found");
            }
            Customers customer = customersService.getCustomersById(reservations.getCustomers_id());
            Optional<Invoices> invoices = invoicesService.getInvoicesByCustomerAndReservation(customer, reservations);
            Invoices invoice = new Invoices();
            if(invoices.isPresent()){
                invoice = invoices.stream().findFirst().get();
            }
            return ResponseEntity.status(200).body(new InvoicesDTO(invoice));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

//    @CrossOrigin
    @PostMapping("/updateOrderDetail")
    public ResponseEntity updateOrder(@RequestBody Invoices_detailsDTO invoicesDetailsDTO)
    {
        try {
            Invoice_details invoiceDetails = invoice_detailsService.getInvoice_detailsById(invoicesDetailsDTO.getId());
            if (invoiceDetails == null){
                return ResponseEntity.status(404).body("not found");
            }
            if(invoiceDetails.getStatus() == "RECEIVED"|| invoiceDetails.getStatus()== "ORDERED"){
                return ResponseEntity.status(304).body("Can't change anything");
            }
            int new_quantity = invoicesDetailsDTO.getQuantity();
            String new_note = invoicesDetailsDTO.getNote();
            if(new_note != null){
                invoiceDetails.setNote(new_note);
            }

            if(new_quantity ==0){
                invoice_detailsService.delete(invoiceDetails.getId());
                return ResponseEntity.status(HttpStatus.OK).body("Complete delete order");
            }
            else {
                if (new_quantity ==0)
                invoiceDetails.setQuantity(new_quantity);
                double new_price = new_quantity * invoiceDetails.getDishes().getPrice();
                invoiceDetails.setPrice(new_price);
                invoice_detailsService.save(invoiceDetails);
                invoicesDetailsDTO.setPrice(new_price);
            }
            Invoices invoices = invoicesService.getInvoicesById(invoicesDetailsDTO.getInvoices_id());
            invoices.setTotal_amount(invoices.calTotalPrice());
            invoicesService.save(invoices);
            return ResponseEntity.status(HttpStatus.OK).body(invoicesDetailsDTO);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
    }

}
