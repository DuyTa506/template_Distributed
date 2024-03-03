package mta.qlnh.Admin.Controller;

import mta.qlnh.Admin.DO.Invoice_details;
import mta.qlnh.Admin.DAO.Invoice_detailsService;
import mta.qlnh.Admin.DataGateway.Invoice_detailsGW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/admin/invoice_details/")
public class Invoice_detailsController {
    @Autowired
    private Invoice_detailsService service;
    private Invoice_detailsGW gateway = new Invoice_detailsGW();
    @CrossOrigin
    @GetMapping("/")
    public List<Invoice_details> getAll()
    {
        return gateway.getAll();
    }


    @PostMapping("/add")
    public ResponseEntity addInvoice_details(@RequestBody Invoice_details invoice_details) throws Exception
    {
        try {
            int result = gateway.addInvoice_details(invoice_details);
            if (result ==200)
                return ResponseEntity.status(200).body(result);
            else
                return ResponseEntity.status(404).body("Not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteInvoice_details(@PathVariable int id)
    {
        try {
            int result = gateway.deleteInvoice_details(id);
            if (result ==200)
                return ResponseEntity.status(200).body("Delete successfully");
            else
                return ResponseEntity.status(404).body("Not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }

    }

    @PutMapping("/update")
    public ResponseEntity updateInvoice_details(@RequestBody Invoice_details invoice_details)
    {
        try {
            int result = gateway.updateInvoice_details(invoice_details);
            if (result ==200)
                return ResponseEntity.status(200).body("update successfully");
            else
                return ResponseEntity.status(404).body("Not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }
}
