package mta.qlnh.Admin.Repo;

import mta.qlnh.Admin.DO.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users,Integer> {
    List<Users> findByUsernameAndPassword(String usn, String pwd);
    Optional<Users> findByUsername(String usn);
    Optional<Users> findById(int id);

}
