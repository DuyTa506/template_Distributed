package mta.qlnh.Admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mta.qlnh.Admin.DO.Users;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Staff4statisticDTO {

    private int id;

    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String position;
    private double salary;
    private String status;
    private int SoLanPhucVu;
    public Staff4statisticDTO(Users u){
        id = u.getId();
        firstname = u.getFirstname();
        lastname = u.getLastname();
        email = u.getEmail();
        phone = u.getPhone();
        position = u.getPosition();
        salary = u.getSalary();
        status = u.getStatus();
        SoLanPhucVu = u.getFreq();
    }
}

