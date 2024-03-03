package mta.qlnh.Admin.Service;

import mta.qlnh.Admin.DO.Customers;
import mta.qlnh.Admin.DO.Invoices;
import mta.qlnh.Admin.DO.Reservations;
import mta.qlnh.Admin.DO.Users;
import mta.qlnh.Admin.DTO.*;
import mta.qlnh.Admin.DAO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/sub/api/admin/cash/")
public class CashAPI {

    @Autowired
    private Invoice_detailsService invoice_detailsService;
    @Autowired
    private InvoicesService invoicesService;
    @Autowired
    private ReservationsService reservationsService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private CustomersService customersService;

//    @CrossOrigin
//    @GetMapping("/")
//    public ResponseEntity getallBill(){
//        try{
//            List<Invoices> invoices= invoicesService.getByPayment("PAID");
//            List<Invoices> x = invoices.stream().toList();
//            List<InvoicesDTO> invoicesDTOS = new ArrayList();
//            for (Invoices item: x
//                 ) {
//                InvoicesDTO t = new InvoicesDTO(item);
//                invoicesDTOS.add(t);
//            }
//            return ResponseEntity.status(200).body(invoicesDTOS);
//        }
//        catch (Exception e){
//            return ResponseEntity.status(500).body("Internal server error");
//        }
//    }


    @GetMapping("/")
    public ResponseEntity getBillWithFilter(@RequestParam(name="startDay", required = false)String startD,
                                            @RequestParam(name="endDay", required = false)String endD,
                                            @RequestParam(name="startHour", required = false)String startH,
                                            @RequestParam(name="endHour", required = false)String endH,
                                            @RequestParam(name="customerName", required = false)String customerName,
                                            @RequestParam(name="customerNum", required = false)String customerNum){
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            if(startD == null) startD = "01-01-2020";
            if(endD == null) endD = "31-12-2023";
            if(customerName == null) customerName = "";
            if(customerNum == null) customerNum = "";

            Date startDate = dateFormat.parse(startD);
            Date endDate = dateFormat.parse(endD);

            List<Invoices> invoices= invoicesService.getByPayment("PAID");
            List<Invoices> x = invoices.stream().toList();
            List<InvoicesDTO> invoicesDTOS = new ArrayList();
            for (Invoices item: x
            ) {
                Date c_date = item.getDate();

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(c_date);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);

                String c_num = item.getCustomers().getPhone();
                if (c_num == null) c_num = "000";
                if((c_date.after(startDate) ||c_date.equals(startDate)) && (c_date.before(endDate) || c_date.equals(endDate))
                        && item.getCustomers().getName().contains(customerName)
                        && c_num.contains(customerNum)
                )
                {
                    if(endH == null && startH == null){
                        InvoicesDTO t = new InvoicesDTO(item);
                        invoicesDTOS.add(t);
                    } else if( endH != null && startH == null){
                        if(Integer.parseInt(endH) >= hour){
                            InvoicesDTO t = new InvoicesDTO(item);
                            invoicesDTOS.add(t);
                        }
                    } else if (endH == null && startH != null){
                        if(Integer.parseInt(startH)<= hour){
                            InvoicesDTO t = new InvoicesDTO(item);
                            invoicesDTOS.add(t);
                        }
                    } else if (endH != null && startH != null){
                        if(Integer.parseInt(startH) <= hour && Integer.parseInt(endH)>= hour){
                            InvoicesDTO t = new InvoicesDTO(item);
                            invoicesDTOS.add(t);
                        }
                    }
                }
            }
            return ResponseEntity.status(200).body(invoicesDTOS);
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
            if (invoicesService.check(id) == false){
                return ResponseEntity.status(404).body("Not found");
            }
            Invoices t = invoicesService.getInvoicesById(id);
            return ResponseEntity.status(200).body(new InvoicesDTO(t));
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


    // Khách hàng ra khỏi bàn, đòi tạo hóa đơn, nhưng chưa thanh toán
//    @CrossOrigin
    @PostMapping("/ClearReservation/{id}")
    public ResponseEntity  ClearCurrenReservation(@PathVariable int id){
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
                invoice.setPayment("UNPAID");
                int uid = invoice.getUsers().getId();
                invoice.setGetout(new Date());
                reservations.setCustomers_id(0);
                reservations.setStatus("0");
                invoicesService.save(invoice);
                reservationsService.save(reservations);
                Users u = usersService.getUserById(uid).get();
                u.setFreq(u.getFreq()+1);
                usersService.save(u);
            }
            return ResponseEntity.status(200).body(new InvoicesDTO(invoice));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

//    @CrossOrigin
    @PostMapping("/paid/{id}")
    public ResponseEntity  PaymentConfirm(@PathVariable int id){
        try{

            if (invoicesService.check(id) == false){
                return ResponseEntity.status(404).body("Not found");
            }
            Invoices t = invoicesService.getInvoicesById(id);
            t.setPayment("PAID");
            invoicesService.save(t);
            return ResponseEntity.status(200).body(new InvoicesDTO(t));
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Internal server error");
        }
    }


}
