package fi.metropolia.ilkkas.demo.repository;

import fi.metropolia.ilkkas.demo.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer>{
}
