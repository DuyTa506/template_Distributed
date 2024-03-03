package mta.qlnh.Admin.Repo;

import mta.qlnh.Admin.DO.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DishesRepo extends JpaRepository<Dishes,Integer> {
    @Query(value = "SELECT d FROM Dishes d WHERE d.name LIKE %:keyword%")
    List<Dishes> findDishesByName(@Param("keyword") String key);
    @Query(value = "SELECT d FROM Dishes d JOIN d.invoice_details id JOIN id.invoices i WHERE i.id = :invoiceId")
    List<Dishes> getDishesByInvoices (@Param("invoiceId") int id);
}
