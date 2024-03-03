package mta.qlnh.Admin.Controller;

import mta.qlnh.Admin.DO.Dishes;
import mta.qlnh.Admin.DTO.DishesDTO;
import mta.qlnh.Admin.DO.Dishtypes;
import mta.qlnh.Admin.DAO.DishesService;
import mta.qlnh.Admin.DAO.DishtypesService;
import mta.qlnh.Admin.DataGateway.DishesGW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/admin/dishes/")
public class DishesController {

    DishesGW gateway = new DishesGW();

    @GetMapping("/")
    public List<DishesDTO> getAll() {
        return gateway.getAll();
}
    @GetMapping("/getbyid/{id}")
    public Dishes getDishesById(@PathVariable int id){
        return gateway.getById(id);
    }
    @GetMapping("/search/")
    public List<Dishes> searchDishes(@RequestParam("name") String keyword){
        return gateway.searchDishes(keyword);
    }
    @GetMapping("/dishesbyinvoices/{id}")  //Đưa ra list món ăn của một hóa đơn
    public List<Dishes> getDishesofinvoice(@PathVariable int id){
        return  gateway.getDishesofinvoice(id);
    }
    public static String UPLOAD_DIRECTORY= "./Image";
//    @PostMapping("/add")
//    public ResponseEntity addDishes(@ModelAttribute Dishes dishes, @RequestParam(value = "file",required = false) MultipartFile file) throws Exception {
//        if(dishes==null){
//            return ResponseEntity.status(200).body("Thêm món ăn không thành công!");
//        }
//        if(file==null)
//        {
//            service.save(dishes);
//            return ResponseEntity.status(200).body("Thêm mới món ăn thành công!");
//        }
//        else {
//            String typedir = Integer.toString(dishes.getDishtypes().getId());
//            Path path = Paths.get(UPLOAD_DIRECTORY, typedir);
//            if (!Files.exists(path)) {
//                Files.createDirectory(path);
//            }
//            LocalDateTime time = LocalDateTime.now();
//            DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
//            String filename = time.format(formatter) +".jpg";
//            Path fileNameAndPath = Paths.get(path.toString(), filename);
//            try {
//                Files.write(fileNameAndPath, file.getBytes());
//                dishes.setImage(fileNameAndPath.toString());
//                service.save(dishes);
//                return ResponseEntity.status(200).body("Thêm món ăn thành công !");
//            } catch (Exception e) {
//
//                return ResponseEntity.status(200).body("Thêm món ăn không thành công!");
//            }
//        }
//    }
    @PostMapping("/add")
    public ResponseEntity addDishes(@RequestBody Dishes dishes) throws Exception {
        try {
            int result = gateway.addDishes(dishes);
            if (result ==200)
                return ResponseEntity.status(200).body("Succesfully");
            else
                return ResponseEntity.status(404).body("Not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDishes(@PathVariable int id) {
        try {
            int result = gateway.deleteDishes(id);
            if (result ==200)
                return ResponseEntity.status(200).body("Succesfully");
            else
                return ResponseEntity.status(404).body("Not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }
//    @PutMapping("/update")
//    public ResponseEntity updateDishes(@ModelAttribute Dishes dishes, @RequestParam(value = "file",required = false) MultipartFile file) throws Exception {
//        if (file == null) {
//            return ResponseEntity.status(200).body("Tải ảnh lên không thành công!");
//        }
//        else {
//            String typedir = Integer.toString(dishes.getDishtypes().getId());
//            Path path = Paths.get(UPLOAD_DIRECTORY, typedir);//tạo ra path cho từng loại món ăn
//            if (!Files.exists(path)) {
//                Files.createDirectory(path);
//            }
//            LocalDateTime time = LocalDateTime.now();
//            DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
//            String filename = time.format(formatter) +".jpg";//tên ảnh
//            Path fileNameAndPath = Paths.get(path.toString(), filename);//path và tên file ảnh
//            try {
//                Files.write(fileNameAndPath, file.getBytes());
//                Dishtypes dishtypes = dishtypesService.getDishtypesById(dishes.getDishtypes().getId());
//                Dishes updatedishes = service.getDishesById(dishes.getId());
//                updatedishes.setName(dishes.getName());
//                updatedishes.setDishtypes(dishtypes);
//                updatedishes.setDescription(dishes.getDescription());
//                updatedishes.setPrice(dishes.getPrice());
//                updatedishes.setImage(fileNameAndPath.toString());
//                service.save(updatedishes);
//                return ResponseEntity.status(200).body("Sửa món ăn thành công");
//            } catch (Exception e) {
//                return ResponseEntity.status(304).body("Sửa món ăn thất bại");
//            }
//        }
//    }
    @PutMapping("/update")
    public ResponseEntity updateDishpes(@RequestBody Dishes dishes)
    {
        try {
            int result = gateway.addDishes(dishes);
            if (result ==200)
                return ResponseEntity.status(200).body("Succesfully");
            else
                return ResponseEntity.status(404).body("Not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }
}
