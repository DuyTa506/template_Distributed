package mta.qlnh.Admin.Repo;

import mta.qlnh.Admin.DO.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionsRepo extends JpaRepository<Permissions,Integer> {
    List<Permissions> findByName(String name);
    List<Permissions> findByNameContainingAndDescriptionContaining(String n, String d);
}
