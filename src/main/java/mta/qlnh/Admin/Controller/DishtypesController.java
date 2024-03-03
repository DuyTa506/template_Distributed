package mta.qlnh.Admin.Controller;

import mta.qlnh.Admin.DO.Dishtypes;
import mta.qlnh.Admin.DAO.DishtypesService;
import mta.qlnh.Admin.DataGateway.DishtypesGW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/admin/dishtypes/")
public class DishtypesController {
    @Autowired
    private DishtypesService service;
    private DishtypesGW gateway = new DishtypesGW();

    @GetMapping("/")
    public List<Dishtypes> getAll() {
        List<Dishtypes> listDishtypes = gateway.getAll("", "");
        return listDishtypes;
    }


    @PostMapping("/add")
    public ResponseEntity addDishtypes(@RequestBody Dishtypes dishtypes) throws Exception {
        try {
            int result = gateway.addDishtypes(dishtypes);
            if (result ==200)
                return ResponseEntity.status(200).body("Thêm mới nhóm món ăn thành công!"+dishtypes.getId());
            else
                return ResponseEntity.status(404).body("Not found");
        } catch (Exception e) {
            return ResponseEntity.status(304).body("Thêm mới nhóm món ăn không thành công!");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDishtypes(@PathVariable int id) {
        try {
            int result = gateway.deleteDishtypes(id);
            if (result ==200)
                return ResponseEntity.status(200).body("Thêm mới nhóm món ăn thành công!");
            else
                return ResponseEntity.status(404).body("Not found");
        } catch (Exception e) {
            return ResponseEntity.status(304).body("Thêm mới nhóm món ăn không thành công!");
        }
    }
    @PutMapping("/update")
    public ResponseEntity updateDishtypes(@RequestBody Dishtypes dishtypes)
    {
        try {
            int result = gateway.updateDishtypes(dishtypes);
            if (result ==200)
                return ResponseEntity.status(200).body("Thêm mới nhóm món ăn thành công!"+dishtypes.getId());
            else
                return ResponseEntity.status(404).body("Not found");
        } catch (Exception e) {
            return ResponseEntity.status(304).body("Thêm mới nhóm món ăn không thành công!");
        }
    }

}
