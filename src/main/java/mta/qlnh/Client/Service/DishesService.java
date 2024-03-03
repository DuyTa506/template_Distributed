package mta.qlnh.Client.Service;

import jakarta.transaction.Transactional;
import mta.qlnh.Client.Entity.Dishes;
import mta.qlnh.Client.Repository.DishesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DishesService {
    @Autowired
    private DishesRepo repo;
    public List<Dishes> listAll() {
        return repo.findAll();
    }

    public void save(Dishes dishes) {
        repo.save(dishes);
    }

    public Dishes getDishesById(int id) {
        return repo.findById(id).get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
