package mta.qlnh.Admin.DAO;

import jakarta.transaction.Transactional;
import mta.qlnh.Admin.DO.Invoice_details;
import mta.qlnh.Admin.DO.Invoices;
import mta.qlnh.Admin.Repo.Invoice_detailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class Invoice_detailsService {
    @Autowired
    private Invoice_detailsRepo repo;
    public List<Invoice_details> listAll() {
        return repo.findAll();
    }

    public void save(Invoice_details invoice_details) {
        repo.save(invoice_details);
    }

    public Invoice_details getInvoice_detailsById(int id) {
        return repo.findById(id).get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public Optional<Invoice_details> getByInvoiceIdAndStatus(Invoices inv_id, String status){
        return repo.findByinvoicesAndStatusContaining(inv_id, status);
    }
}
