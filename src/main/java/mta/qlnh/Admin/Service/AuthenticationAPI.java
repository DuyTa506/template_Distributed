package mta.qlnh.Admin.Service;

import mta.qlnh.Admin.DO.UserSEC;
import mta.qlnh.Admin.Repo.UserSECRepo;
import mta.qlnh.Admin.DAO.AuthenticationService;
import mta.qlnh.Admin.DTO.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/sub/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationAPI {

    private final AuthenticationService service;

    private final UserSECRepo usersRepo;

    @PostMapping("/register")
    public ResponseEntity register(
            @RequestBody RegisterRequest request
    ) {
        Optional<UserSEC> check = usersRepo.findByUsername(request.getUsername());
        if(check.isPresent()){
            return ResponseEntity.status(409).body("User exist");
        }
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
//        return null;
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);

    }

    @GetMapping("/logout")
    public ResponseEntity logout() {

        return ResponseEntity.ok("logout");

    }


}
