package mta.qlnh.Admin.Controller;

import mta.qlnh.Admin.DO.Invoices;
import mta.qlnh.Admin.DAO.InvoicesService;
import mta.qlnh.Admin.DataGateway.InvoicesGW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/admin/invoices/")
public class InvoicesController {
    private InvoicesGW gateway = new InvoicesGW();
    @CrossOrigin
    @GetMapping("/")
    public List<Invoices> getAll()
    {
        List<Invoices> listInvoices =gateway.getAll();
        return listInvoices;
    }
    @GetMapping("/getByCustormersId/{id}")   //Đưa ra list hóa đơn của một khách hàng bằng id của khách hàng
    public List<Invoices> getByCustormersId(@PathVariable int id) {
        return gateway.getByCustormersId(id);
    }
    @GetMapping("/getbyid/{id}")   //Đưa ra hóa đơn bằng id của hóa đơn
    public Invoices getById(@PathVariable int id){
        return gateway.getById(id);
    }
    @PostMapping("/add")
    public ResponseEntity addInvoices(@RequestBody Invoices invoices) throws Exception
    {
        try {
            int result = gateway.addInvoices(invoices);
            if (result ==200)
                return ResponseEntity.status(200).body("Add successfully");
            else
                return ResponseEntity.status(404).body("Not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteInvoices(@PathVariable int id) {
        try {

            int result = gateway.deleteInvoices(id);
            if (result == 200)
                return ResponseEntity.status(200).body("Delete successfully");
            else
                return ResponseEntity.status(404).body("Not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }


    @PutMapping("/update")
    public ResponseEntity updateInvoices(@RequestBody Invoices invoices)
    {
        try {

            int result = gateway.updateInvoices(invoices);
            if (result == 200)
                return ResponseEntity.status(200).body("Update successfully");
            else
                return ResponseEntity.status(404).body("Not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }
}
