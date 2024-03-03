package mta.qlnh.Admin.Controller;

import mta.qlnh.Admin.DO.Customers;
import mta.qlnh.Admin.DAO.CustomersService;
import mta.qlnh.Admin.DO.Invoices;
import mta.qlnh.Admin.DTO.InvoicesDTO;
import mta.qlnh.Admin.DataGateway.CustomersGW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/admin/customers/")
public class CustomersController {
    @Autowired
    private CustomersService service;

    private  CustomersGW gateway = new CustomersGW();
    @GetMapping("/")
    public List<Customers> getAll()
    {
        return gateway.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getByID (@PathVariable int id){
        try {
            Invoices result = new Invoices();
            result = gateway.getByID(id);
            if (result != null)
                return ResponseEntity.status(200).body(result);
            else
                return ResponseEntity.status(404).body("Not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @GetMapping("/getInvoicesByCustormersId/{id}")
    public Customers getInvoicesByCusID(@PathVariable int id){
        return gateway.getInvoicesByCusID(id);
    }
    @PostMapping("/add")
    public ResponseEntity addCustomers(@RequestBody Customers customers) throws Exception
    {
        try {
            int result = gateway.addCustomers(customers);
            if (result ==200)
                return ResponseEntity.status(200).body(result);
            else
                return ResponseEntity.status(404).body("Not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteuser(@PathVariable int id)
    {
        try {
            int result = gateway.deleteuser(id);
            if (result ==200)
                return ResponseEntity.status(200).body(result);
            else
                return ResponseEntity.status(404).body("Not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }

    }

    @PutMapping("/update")
    public ResponseEntity updateCustomers(@RequestBody Customers user)
    {
        try {
            Customers customerUpdate = service.getCustomersById(user.getId());
            customerUpdate.setName(user.getName());
            customerUpdate.setName(user.getName());
            customerUpdate.setPhone(user.getPhone());
            customerUpdate.setEmail(user.getEmail());
            service.save(customerUpdate);
            return ResponseEntity.status(200).body("Sửa khách hàng thành công");
        }catch (Exception e) {
            return ResponseEntity.status(304).body("Sửa khách hàng thất bại");
        }
    }

}
