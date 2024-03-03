package mta.qlnh.Client.Service;

import jakarta.transaction.Transactional;
import mta.qlnh.Client.Entity.Customers;
import mta.qlnh.Client.Repository.CustomersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CustomerService {
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
