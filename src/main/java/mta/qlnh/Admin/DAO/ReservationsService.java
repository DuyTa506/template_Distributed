package mta.qlnh.Admin.DAO;

import jakarta.transaction.Transactional;
import mta.qlnh.Admin.DO.Reservations;
import mta.qlnh.Admin.Repo.ReservationsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReservationsService {
    @Autowired
    private ReservationsRepo repo;
    public List<Reservations> listAll() {
        return repo.findAll();
    }

    public void save(Reservations reservations) {
        repo.save(reservations);
    }

    public Reservations getReservationsById(int id) {
        return repo.findById(id).get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}
