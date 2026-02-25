package fi.metropolia.ilkkas.demo.repository;

import fi.metropolia.ilkkas.demo.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customers, Integer> {

}
