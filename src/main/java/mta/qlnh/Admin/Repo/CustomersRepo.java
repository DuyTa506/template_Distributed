package mta.qlnh.Admin.Repo;

import mta.qlnh.Admin.DO.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepo extends JpaRepository<Customers,Integer> {
}
