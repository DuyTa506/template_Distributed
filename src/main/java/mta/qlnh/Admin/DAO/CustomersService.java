package mta.qlnh.Admin.DAO;

import jakarta.transaction.Transactional;
import mta.qlnh.Admin.DO.Customers;
import mta.qlnh.Admin.Repo.CustomersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CustomersService {
    @Autowired
    private CustomersRepo repo;

    public List<Customers> listAll() {
        return repo.findAll();
    }

    public void save(Customers customers) {
        repo.save(customers);
    }

    public Customers getCustomersById(int id) {
        return repo.findById(id).get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
