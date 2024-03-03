package mta.qlnh.Admin.Service;

import mta.qlnh.Admin.DO.Invoice_details;
import mta.qlnh.Admin.DAO.Invoice_detailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/sub/api/admin/invoice_details/")
public class Invoice_detailsAPI {
    @Autowired
    private Invoice_detailsService service;
    @CrossOrigin
    @GetMapping("/")
    public List<Invoice_details> getAll()
    {
        List<Invoice_details> listInvoice_details =service.listAll();
        return listInvoice_details;
    }


    @PostMapping("/add")
    public ResponseEntity addInvoice_details(@RequestBody Invoice_details invoice_details) throws Exception
    {
        try
        {
            service.save(invoice_details);
            return ResponseEntity.status(200).body("Thêm mới chi tiết hóa đơn thành công!");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(304).body("Thêm mới chi tiết hóa đơn không thành công!");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteInvoice_details(@PathVariable int id)
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
    public ResponseEntity updateInvoice_details(@RequestBody Invoice_details invoice_details)
    {
        try {
            Invoice_details invoice_detailsUpdate = service.getInvoice_detailsById(invoice_details.getId());
            invoice_detailsUpdate.setDishes(invoice_details.getDishes());
            invoice_detailsUpdate.setInvoices(invoice_details.getInvoices());
            invoice_detailsUpdate.setQuantity(invoice_details.getQuantity());
            invoice_detailsUpdate.setPrice(invoice_details.getPrice());
            service.save(invoice_detailsUpdate);
            return ResponseEntity.status(200).body("Sửa chi tiết hóa đơn thành công");
        }catch (Exception e) {
            return ResponseEntity.status(304).body("Sửa chi tiết hóa đơn thất bại");
        }
    }
}
