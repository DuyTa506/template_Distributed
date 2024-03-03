package mta.qlnh.Client.Repository;

import mta.qlnh.Client.Entity.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishesRepo extends JpaRepository<Dishes,Integer> {
}
