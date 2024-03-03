package mta.qlnh.Admin.Service;

import jakarta.servlet.http.HttpServletRequest;
import mta.qlnh.Admin.DAO.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sub/api/admin/logout/")
public class LogoutAPI {
    @Autowired
    private TokenService service;

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @GetMapping("/")
    public ResponseEntity logout(HttpServletRequest request)
    {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        String jwt = authHeader.substring(7);
        boolean tok = service.getToken(jwt);
        if(tok == true){
            return ResponseEntity.status(HttpStatus.OK).body("Log out");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}
