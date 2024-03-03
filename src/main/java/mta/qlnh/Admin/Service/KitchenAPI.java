package mta.qlnh.Admin.Service;

import mta.qlnh.Admin.DO.Invoice_details;
import mta.qlnh.Admin.DO.Reservations;
import mta.qlnh.Admin.DTO.*;
import mta.qlnh.Admin.DAO.Invoice_detailsService;
import mta.qlnh.Admin.DAO.InvoicesService;
import mta.qlnh.Admin.DAO.ReservationsService;
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
@RequestMapping("/sub/sub/api/admin/kitchen/")
public class KitchenAPI {

    @Autowired
    private Invoice_detailsService invoice_detailsService;
    @Autowired
    private InvoicesService invoicesService;
    @Autowired
    private ReservationsService reservationsService;

    @CrossOrigin
    @GetMapping("/")
public ResponseEntity  getAll() {

    try {
        List<Invoice_details> invoiceDetailsList = invoice_detailsService.listAll();
        List< Invoice_details_K> invoice_details_kList = new ArrayList<>();
        for (Invoice_details item: invoiceDetailsList
        ) {
            if(!item.getInvoices().getPayment().equals("PAID") ){
                Reservations reservations = reservationsService.getReservationsById(item.getReservation_id());
                Invoice_details_K obj = new Invoice_details_K(item, reservations);
                invoice_details_kList.add(obj);
            }
        }
        return ResponseEntity.status(200).body(invoice_details_kList);
    }
    catch(Exception e){
        return ResponseEntity.status(404).body("Not found !");
    }
}

    @CrossOrigin
    @GetMapping("/byId/")
    public ResponseEntity  getAll(@RequestParam(name="dishtype_id", required = false)int t) {

        try {
            List<Invoice_details> invoiceDetailsList = invoice_detailsService.listAll();
            List< Invoice_details_K> invoice_details_kList = new ArrayList<>();
            for (Invoice_details item: invoiceDetailsList
            ) {
                if(!item.getInvoices().getPayment().equals("PAID") ){
                    Reservations reservations = reservationsService.getReservationsById(item.getReservation_id());
                    Invoice_details_K obj = new Invoice_details_K(item, reservations);
                    invoice_details_kList.add(obj);
                }
            }
            List< Invoice_details_K> result = invoice_details_kList.stream()
                    .filter(obj -> obj.getDishes_type_id()==t)
                    .collect(Collectors.toList());
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
            Invoice_details invoiceDetails = invoice_detailsService.getInvoice_detailsById(order_detail_id);
            invoiceDetails.setAcceptedAt(Time.valueOf(LocalTime.now()));
            invoiceDetails.setStatus("ACCEPTED");
            invoice_detailsService.save(invoiceDetails);
            return ResponseEntity.status(200).body(new Invoices_detailsDTO(invoiceDetails));
        }
        catch (Exception e){
            return ResponseEntity.status(404).body("Not found");
        }
    }

    @CrossOrigin
    @PostMapping("/cooked")
    public ResponseEntity cooked(@RequestBody OrderObjectRequest obj){
        try {
            Invoice_details invoiceDetails = invoice_detailsService.getInvoice_detailsById(obj.getDetail_id());
            invoiceDetails.setCoocked_num(obj.getNum()+ invoiceDetails.getCoocked_num());
            if(invoiceDetails.getCoocked_num() > invoiceDetails.getOrdered_num()){
                invoiceDetails.setStatus("WAITING");
            }

            invoice_detailsService.save(invoiceDetails);
            return ResponseEntity.status(200).body(new Invoices_detailsDTO(invoiceDetails));
        }
        catch (Exception e){
            return ResponseEntity.status(304).body("Không tìm thấy hoặc số lượng lớn hơn số được order!");
        }
    }

    @CrossOrigin
    @PostMapping("/ordered")
    public ResponseEntity ordered(@RequestBody OrderObjectRequest obj){
        try {
            Invoice_details invoiceDetails = invoice_detailsService.getInvoice_detailsById(obj.getDetail_id());
            invoiceDetails.setOrdered_num(obj.getNum()+ invoiceDetails.getOrdered_num());
            if(invoiceDetails.getOrdered_num() == invoiceDetails.getQuantity()){
                invoiceDetails.setStatus("RECEIVED");
                invoiceDetails.setReceivedAt(Time.valueOf(LocalTime.now()));
//                return ResponseEntity.status(200).body(new Invoices_detailsDTO(invoiceDetails));
            }
            if(invoiceDetails.getCoocked_num() > invoiceDetails.getOrdered_num()){
                invoiceDetails.setStatus("WAITING");
            }
            else {
                invoiceDetails.setStatus("ACCEPTED");
            }
            invoice_detailsService.save(invoiceDetails);
            return ResponseEntity.status(200).body(new Invoices_detailsDTO(invoiceDetails));
        }
        catch (Exception e){
            return ResponseEntity.status(304).body("Không tìm thấy hoặc số lượng lớn hơn số được order!");
        }
    }


}
