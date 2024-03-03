package mta.qlnh.Client.Repository;

import mta.qlnh.Client.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users,Integer> {
}
