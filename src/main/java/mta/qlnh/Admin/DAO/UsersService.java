package mta.qlnh.Admin.DAO;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import mta.qlnh.Admin.DO.Users;
import mta.qlnh.Admin.Repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsersService {
    @Autowired
    private UsersRepo repo;

    public List<Users> listAll() {
        return repo.findAll();
    }

    public void save(Users users) {
        repo.save(users);
    }

    public Optional<Users> getUserById(int id) {
        return repo.findById(id);
    }


    public boolean checkUserExist(String usn)
    {
        Optional<Users> users = repo.findByUsername(usn);
        if(users.isPresent()){
            return true;
        }
        return false;
    }
    public void delete(int id) {
        repo.deleteById(id);
        }
    public Optional<Users> getUserByUsername(String usn){
        return repo.findByUsername(usn);
    }

    public Users getByUsername(String usn){
        return repo.findByUsername(usn).stream().toList().get(0);
    }
    public Optional<Users> getById(int id){
        return repo.findById(id);
    }


//    public boolean checkLogin(Users userForm){
//        List<Users> users = repo.findByUsernameAndPassword(userForm.getUsername(), userForm.getPassword());
//        if (users!= null && users.size() > 0){
//            return true;
//        } else {
//            return false;
//        }
//    }

//    public Users loadUserByUsername(String usn){
//        return repo.findByUsername(usn).get(0);
//    }

}
