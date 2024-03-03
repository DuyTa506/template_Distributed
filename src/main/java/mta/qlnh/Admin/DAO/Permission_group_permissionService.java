package mta.qlnh.Admin.DAO;

import jakarta.transaction.Transactional;
import mta.qlnh.Admin.DO.Permission_group_permissions;
import mta.qlnh.Admin.DTO.*;
import java.util.*;
import mta.qlnh.Admin.Repo.Permission_group_permissionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class Permission_group_permissionService {
    @Autowired
    private Permission_group_permissionsRepo repo;
    public List<Permission_group_permissions> listAll() {
        return repo.findAll();
    }

    public void saveList(List<Permission_group_permissions> ps){
        for (Permission_group_permissions item :
                ps) {
            repo.save(item);
        }
    }

    public void deleteList(List<Permission_group_permissions> ps){
        for (Permission_group_permissions item :
                ps) {
            CompositeKey t = new CompositeKey(item.getPermission_group_id(), item.getPermission_id());
            this.delete(t);
        }
    }
    public void save(Permission_group_permissions Permission_group_permission) {
        repo.save(Permission_group_permission);
    }

    public void delete(CompositeKey key){
        Optional<Permission_group_permissions> x = repo.findById(key);
        x.ifPresent(entity ->{
            repo.delete(entity);
        });
    }

}
