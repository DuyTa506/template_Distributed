package mta.qlnh.Admin.DAO;

import jakarta.transaction.Transactional;
import mta.qlnh.Admin.DO.Dishtypes;
import mta.qlnh.Admin.Repo.DishtypesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DishtypesService {
    @Autowired
    private DishtypesRepo repo;
    public List<Dishtypes> listAll() {
        return repo.findAll();
    }

    public void save(Dishtypes dishtypes) {
        repo.save(dishtypes);
    }

    public Dishtypes getDishtypesById(int id) {
        return repo.findById(id).get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
