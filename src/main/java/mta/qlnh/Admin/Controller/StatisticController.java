package mta.qlnh.Admin.Controller;

import mta.qlnh.Admin.DO.*;
import mta.qlnh.Admin.DTO.*;
import mta.qlnh.Admin.DAO.CustomersService;
import mta.qlnh.Admin.DAO.DishesService;
import mta.qlnh.Admin.DAO.Invoice_detailsService;
import mta.qlnh.Admin.DAO.InvoicesService;
import mta.qlnh.Admin.DAO.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/admin/statistics/")
public class StatisticController {
    @Autowired
    private CustomersService customersService;

    @Autowired
    private InvoicesService invoicesService;

    @Autowired
    private Invoice_detailsService invoice_detailsService;

    @Autowired
    private UsersService usersService;
    @Autowired
    private DishesService dishesService;

    @GetMapping("/customerNumbers")
    public ResponseEntity getAll() {
        try {
            List<Customers> ls = customersService.listAll();
            statisticDTO1 result = new statisticDTO1();
            result.setCustomerNum((int) ls.stream().count());
            statisticDTO1 s = new statisticDTO1();
            return ResponseEntity.status(200).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e);
        }

    }

    @GetMapping("/todayRevenue")
    public ResponseEntity getMoneys() {
        try {
            Calendar calendarr = Calendar.getInstance();
            Date today = calendarr.getTime();
            List<Invoices> invoices = invoicesService.getByPayment("PAID");
            List<Invoices> x = invoices.stream().toList();
            List<InvoicesDTO> invoicesDTOS = new ArrayList();
            int revenue = 0;
            for (Invoices item : x
            ) {
                Date c_date = item.getDate();

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(c_date);

                if(c_date.getDate() == today.getDate() && c_date.getMonth() == today.getMonth() && c_date.getYear()== today.getYear()&& item.getPayment().equals("PAID")){
                    InvoicesDTO t = new InvoicesDTO(item);
                    invoicesDTOS.add(t);
                    revenue += t.getTotal_amount();
                }


            }
            statisticDTO2 s = new statisticDTO2();
            s.setToday_revenue(revenue);
            return ResponseEntity.status(200).body(s);
        } catch (
                Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }

    }
    @GetMapping("/DishesFrequency")
    public ResponseEntity getDishFrequencies() {
        try{
            List<Invoice_details> xs = invoice_detailsService.listAll();
            List<Dishes> dishes = dishesService.listAll();
            List<Dish4statisticDTO> result = new ArrayList<>();
            for (Dishes item: dishes
            ) {
                result.add(new Dish4statisticDTO(item));
            }
            for (Invoice_details x: xs
            ) {
                for (Dish4statisticDTO d: result
                ) {
                    if(x.getDishes().getId() == d.getId()){
                        d.setNumber(d.getNumber() + x.getQuantity());
                    }

                }

            }
            return ResponseEntity.status(200).body(result);
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }

    }
    @GetMapping("/StaffFrequency")
    public ResponseEntity getStaffFrequencies() {
        try{
            List<Users> ls = usersService.listAll();
            List<Staff4statisticDTO> ss = new ArrayList<>();
            for (Users item: ls
                 ) {
                Staff4statisticDTO s = new Staff4statisticDTO(item);
                ss.add(s);
            }
            return ResponseEntity.status(200).body(ss);
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }

    }
}
