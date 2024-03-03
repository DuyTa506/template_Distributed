package mta.qlnh.Admin.DAO;
import java.util.*;

import jakarta.transaction.Transactional;
import mta.qlnh.Admin.DO.Permissions;
import mta.qlnh.Admin.Repo.PermissionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PermissionsService {
    @Autowired
    private PermissionsRepo repo;
    public List<Permissions> listAll(){
        return repo.findAll();
    }
    public void save(Permissions permissions) {
        repo.save(permissions);
    }

    public Permissions getPermissionsById(int id) {
        return repo.findById(id).get();
    }
    public List<Permissions> getPermissionsByName(String name){
        return repo.findByName(name);
    }

    public  List<Permissions> Filter(String n, String d){
        return repo.findByNameContainingAndDescriptionContaining(n,d);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
