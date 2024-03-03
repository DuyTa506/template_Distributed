package mta.qlnh.Admin.Service;

import mta.qlnh.Admin.DO.Permission_group_permissions;
import mta.qlnh.Admin.DAO.Permission_group_permissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/sub/api/admin/permissionToGroups/")
public class Permission_group_permissionsAPI {


        @Autowired
        private Permission_group_permissionService service;

//        @CrossOrigin
        @PostMapping("/add")
        public ResponseEntity addPermissionsToGroup(@RequestBody List<Permission_group_permissions> ps) throws Exception {
            try {
                service.saveList(ps);
                return ResponseEntity.status(200).body("Done!");
            } catch (Exception e) {
                return ResponseEntity.status(304).body("Nope!");
            }
        }
        @PostMapping("/delete")
        public ResponseEntity delPermissionsToGroup(@RequestBody List<Permission_group_permissions> ps) throws Exception {
            try {
                service.deleteList(ps);
                return ResponseEntity.status(200).body("Done!");
            } catch (Exception e) {
                return ResponseEntity.status(304).body("Nope!");
            }
        }
    }

