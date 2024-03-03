package mta.qlnh.Admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mta.qlnh.Admin.DO.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {



    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String position;
    private Role role;
}