package mta.qlnh.Admin.DAO;

import jakarta.transaction.Transactional;
import mta.qlnh.Admin.DO.Customers;
import mta.qlnh.Admin.DO.Invoices;
import mta.qlnh.Admin.DO.Reservations;
import mta.qlnh.Admin.Repo.InvoicesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InvoicesService {
    @Autowired
    private InvoicesRepo repo;
    public List<Invoices> listAll() {
        return repo.findAll();
    }

    public void save(Invoices invoices) {
        repo.save(invoices);
    }

    public Invoices getInvoicesById(int id) {
        return repo.findById(id).get();
    }
    public boolean check(int id){
        if (repo.findById(id).isPresent()){return true;}
        return false;
    }
    public void delete(int id) {
        repo.deleteById(id);
    }
    public List<Invoices> getInvoicesByCustormersId(int id){
        return repo.getInvoicesByCustormersId(id);
    }

    public Optional<Invoices> getInvoicesByCustomerAndReservation(Customers c, Reservations r){
        return repo.findByCustomersAndReservations(c, r);
    }
    public List<Invoices> getByPayment(String payment){
        List<Invoices> t = repo.findByPaymentContaining(payment);
        return t;
    }

}
