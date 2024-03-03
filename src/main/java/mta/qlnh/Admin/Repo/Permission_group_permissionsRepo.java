package mta.qlnh.Admin.Repo;

import mta.qlnh.Admin.DTO.CompositeKey;
import mta.qlnh.Admin.DO.Permission_group_permissions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Permission_group_permissionsRepo extends JpaRepository<Permission_group_permissions, CompositeKey> {
}