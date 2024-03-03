package mta.qlnh.Admin.Repo;

import mta.qlnh.Admin.DO.Customers;
import mta.qlnh.Admin.DO.Invoices;
import mta.qlnh.Admin.DO.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InvoicesRepo extends JpaRepository<Invoices,Integer> {
    @Query(value = "SELECT i FROM Invoices i WHERE i.customers.id=:id ")
    List<Invoices> getInvoicesByCustormersId(@Param("id") int id);

    Optional<Invoices> findByCustomersAndReservations(Customers customer, Reservations reservations);
    List<Invoices> findByPaymentContaining (String payment);
    Optional<Invoices> findById(int id);
}
