package mta.qlnh.Admin.Service;

import mta.qlnh.Admin.DO.Invoices;
import mta.qlnh.Admin.DAO.InvoicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/sub/api/admin/invoices/")
public class InvoiceAPI {
    @Autowired
    private InvoicesService service;
    @CrossOrigin
    @GetMapping("/")
    public List<Invoices> getAll()
    {
        List<Invoices> listInvoices =service.listAll();
        return listInvoices;
    }
    @GetMapping("/getByCustormersId/{id}")   //Đưa ra list hóa đơn của một khách hàng bằng id của khách hàng
    public List<Invoices> getByCustormersId(@PathVariable int id) {
        return service.getInvoicesByCustormersId(id);
    }
    @GetMapping("/getbyid/{id}")   //Đưa ra hóa đơn bằng id của hóa đơn
    public Invoices getById(@PathVariable int id){
        return service.getInvoicesById(id);
    }
    @PostMapping("/add")
    public ResponseEntity addInvoices(@RequestBody Invoices invoices) throws Exception
    {
        try
        {
            service.save(invoices);
            return ResponseEntity.status(200).body("Thêm mới chi tiết hóa đơn thành công!");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(304).body("Thêm mới chi tiết hóa đơn không thành công!");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteInvoices(@PathVariable int id)
    {
        try
        {
            service.delete(id);
            return ResponseEntity.status(200).body("Xóa chi tiết hóa đơn thành công!");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(304).body("Xóa chi tiết hóa đơn không thành công!");
        }

    }

    @PutMapping("/update")
    public ResponseEntity updateInvoices(@RequestBody Invoices invoices)
    {
        try {
            Invoices invoicesUpdate = service.getInvoicesById(invoices.getId());
            invoicesUpdate.setDate(invoices.getDate());
            invoicesUpdate.setTotal_amount(invoices.getTotal_amount());

            service.save(invoicesUpdate);
            return ResponseEntity.status(200).body("Sửa chi tiết hóa đơn thành công");
        }catch (Exception e) {
            return ResponseEntity.status(304).body("Sửa chi tiết hóa đơn thất bại");
        }
    }
}
