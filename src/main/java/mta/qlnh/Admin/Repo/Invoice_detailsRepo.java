package mta.qlnh.Admin.Repo;


import mta.qlnh.Admin.DO.Invoice_details;
import mta.qlnh.Admin.DO.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Invoice_detailsRepo extends JpaRepository<Invoice_details,Integer> {
    Optional<Invoice_details> findByinvoicesAndStatusContaining(Invoices inv_id, String status);
}
