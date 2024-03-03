package mta.qlnh.Admin.DAO;

import java.util.List;

import jakarta.transaction.Transactional;
import mta.qlnh.Admin.DO.UserSEC;
import mta.qlnh.Admin.Repo.UserSECRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserSECService {
    @Autowired
    private UserSECRepo repo;

    public List<UserSEC> listAll() {
        return repo.findAll();
    }

    public void save(UserSEC users) {
        repo.save(users);
    }

    public UserSEC getUserById(int id) {
        return repo.findById(id).get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

}
