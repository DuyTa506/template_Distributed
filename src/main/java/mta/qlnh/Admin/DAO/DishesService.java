package mta.qlnh.Admin.DAO;

import jakarta.transaction.Transactional;
import mta.qlnh.Admin.DO.Dishes;
import mta.qlnh.Admin.Repo.DishesRepo;
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
    public List<Dishes> findDishesByName(String keyword){
        return repo.findDishesByName(keyword);
    }
    public List<Dishes> getDishesByInvoices(int id){
        return repo.getDishesByInvoices(id);
    }

}
