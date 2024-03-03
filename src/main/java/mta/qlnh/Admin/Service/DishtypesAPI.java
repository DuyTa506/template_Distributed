package mta.qlnh.Admin.Service;

import mta.qlnh.Admin.DO.Dishtypes;
import mta.qlnh.Admin.DAO.DishtypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/sub/api/admin/dishtypes/")
public class DishtypesAPI {
    @Autowired
    private DishtypesService service;

    @GetMapping("/")
    public List<Dishtypes> getAll() {
        List<Dishtypes> listDishtypes = service.listAll();
        return listDishtypes;
    }


    @PostMapping("/add")
    public ResponseEntity addDishtypes(@RequestBody Dishtypes dishtypes) throws Exception {
        try {
            service.save(dishtypes);
            return ResponseEntity.status(200).body("Thêm mới nhóm món ăn thành công!"+dishtypes.getId());
        } catch (Exception e) {
            return ResponseEntity.status(304).body("Thêm mới nhóm món ăn không thành công!");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDishtypes(@PathVariable int id) {
        try {
            service.delete(id);
            return ResponseEntity.status(200).body("Xóa nhóm món ăn thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(304).body("Xóa nhóm món ăn không thành công!");
        }
    }
    @PutMapping("/update")
    public ResponseEntity updateDishtypes(@RequestBody Dishtypes dishtypes)
    {
        try {
            Dishtypes updatedishtypes = service.getDishtypesById(dishtypes.getId());
            updatedishtypes.setName(dishtypes.getName());
            service.save(updatedishtypes);
            return ResponseEntity.status(200).body("Cập nhật nhóm món ăn thành công!");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(304).body("Cập nhật nhóm món ăn không thành công!");
        }
    }

}
