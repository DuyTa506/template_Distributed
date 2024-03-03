package mta.qlnh.Admin.Repo;

import mta.qlnh.Admin.DO.Permission_groups;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface Permission_groupsRepo extends JpaRepository<Permission_groups,Integer>  {
    List<Permission_groups> findByName(String name);
    List<Permission_groups> findByNameContainingAndDescriptionContainingAndActionContainingAndStatusContaining(String n, String d, String a,String s);
}
