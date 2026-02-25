package fi.metropolia.ilkkas.demo.repository;

import fi.metropolia.ilkkas.demo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

}
