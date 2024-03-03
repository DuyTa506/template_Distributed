package mta.qlnh.Admin.DAO;
import java.util.*;

import jakarta.transaction.Transactional;
import mta.qlnh.Admin.DO.Permission_groups;
import mta.qlnh.Admin.DO.Permissions;
import mta.qlnh.Admin.Repo.Permission_groupsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class Permission_groups_cliService {
    @Autowired
    private Permission_groupsRepo repo;
    public List<Permission_groups> listAll(){
        return repo.findAll();
    }
    public void save(Permission_groups Permission_groups) {
        repo.save(Permission_groups);
    }

    public Permission_groups getPermission_groupsById(int id) {
        return repo.findById(id).get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    // Lay tat ca quyen cua mot nhom quyen
    public List<Permissions> getPermissionOfGroup(int id){
        Permission_groups group = repo.findById(id).get();
        List<Permissions> pms = group.getGrPermissions();
        return (List<Permissions>) pms;
    }
}
