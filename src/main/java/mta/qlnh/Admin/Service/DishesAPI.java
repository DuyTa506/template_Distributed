package mta.qlnh.Admin.Service;

import mta.qlnh.Admin.DO.Dishes;
import mta.qlnh.Admin.DTO.DishesDTO;
import mta.qlnh.Admin.DO.Dishtypes;
import mta.qlnh.Admin.DAO.DishesService;
import mta.qlnh.Admin.DAO.DishtypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/sub/api/admin/dishes/")
public class DishesAPI {
    @Autowired
    private DishesService service;
    @Autowired
    private DishtypesService dishtypesService;

    @GetMapping("/")
    public List<DishesDTO> getAll() {
        List<Dishes> listDishes = service.listAll();
        List<DishesDTO> result = new ArrayList<>();
        for (Dishes item : listDishes
             ) {
            result.add(new DishesDTO(item));
        }
        return result;
}
    @GetMapping("/getbyid/{id}")
    public Dishes getDishesById(@PathVariable int id){
        return service.getDishesById(id);
    }
    @GetMapping("/search/")
    public List<Dishes> searchDishes(@RequestParam("name") String keyword){
        return service.findDishesByName(keyword);
    }
    @GetMapping("/dishesbyinvoices/{id}")  //Đưa ra list món ăn của một hóa đơn
    public List<Dishes> getDishesofinvoice(@PathVariable int id){
//        TypedQuery<Dishes> query =entityManager.createQuery("SELECT d FROM Dishes d JOIN d.invoice_details id JOIN id.invoices i WHERE i.id = :invoiceId", Dishes.class);
//        query.setParameter("invoiceId", id);
//        return query.getResultList();
        return service.getDishesByInvoices(id);
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
        service.save(dishes);
        return ResponseEntity.status(200).body("Thêm mới món ăn thành công!");
    } catch (Exception e) {
        return ResponseEntity.status(304).body("Thêm mới nhóm món ăn không thành công!");
    }
}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDishes(@PathVariable int id) {
        try {
            service.delete(id);
            return ResponseEntity.status(200).body("Xóa món ăn thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(304).body("Xóa món ăn không thành công!");
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
            Dishtypes dishtypes = dishtypesService.getDishtypesById(dishes.getDishtypes().getId());
            Dishes updatedishes = service.getDishesById(dishes.getId());
            updatedishes.setName(dishes.getName());
            updatedishes.setDishtypes(dishtypes);
            updatedishes.setDescription(dishes.getDescription());
            updatedishes.setPrice(dishes.getPrice());
            updatedishes.setImage(dishes.getImage());
            service.save(updatedishes);
            return ResponseEntity.status(200).body("Sửa món ăn thành công");
        }
        catch (Exception e)
        {
            return ResponseEntity.status(304).body("Cập nhật nhóm món ăn không thành công!");
        }
    }
}
