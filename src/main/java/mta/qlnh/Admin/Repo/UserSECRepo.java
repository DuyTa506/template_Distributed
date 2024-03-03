package mta.qlnh.Admin.Repo;

import mta.qlnh.Admin.DO.UserSEC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserSECRepo extends JpaRepository<UserSEC,Integer> {
    List<UserSEC> findByUsernameAndPassword(String usn, String pwd);
    Optional<UserSEC> findByUsername(String usn);
}
