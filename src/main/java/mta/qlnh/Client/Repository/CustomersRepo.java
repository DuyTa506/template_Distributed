package mta.qlnh.Client.Repository;

import mta.qlnh.Client.Entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepo extends JpaRepository<Customers,Integer> {
}
