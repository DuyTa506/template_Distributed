package mta.qlnh.Client.Service;

import jakarta.transaction.Transactional;
import mta.qlnh.Client.Entity.Users;
import mta.qlnh.Client.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Users getUserById(int id) {
        return repo.findById(id).get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
