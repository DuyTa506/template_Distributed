package mta.qlnh.Admin.Service;

import mta.qlnh.Admin.DO.Customers;
import mta.qlnh.Admin.DAO.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/sub/api/admin/customers/")
public class CustomersAPI {
    @Autowired 
    private CustomersService service;
    @GetMapping("/")
    public List<Customers> getAll()
    {
        List<Customers> listCustomers =service.listAll();
        return listCustomers;
    }

    @GetMapping("/{id}")
    public ResponseEntity getByID (@PathVariable int id){
        try {
            Customers cus = service.getCustomersById(id);
            if (cus == null ) {
                return  ResponseEntity.status(404).body("Not found");
            }
            return ResponseEntity.status(200).body(cus);
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Internal error");
        }
    }

    @GetMapping("/getInvoicesByCustormersId/{id}")
    public Customers getInvoicesByCusID(@PathVariable int id){
        return service.getCustomersById(id);
    }
    @PostMapping("/add")
    public ResponseEntity addCustomers(@RequestBody Customers Customers) throws Exception
    {
        try
        {
            service.save(Customers);
            if (Customers.getName() == null) Customers.setName("Khach Hang");
            if (Customers.getPhone() == null) Customers.setPhone("Not Avaiable");
            return ResponseEntity.status(200).body("Thêm mới khách hàng thành công!");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(304).body("Thêm mới khách hàng không thành công!");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteuser(@PathVariable int id)
    {
        try
        {
            service.delete(id);
            return ResponseEntity.status(200).body("Xóa khách hàng thành công!");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(304).body("Xóa khách hàng không thành công!");
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
