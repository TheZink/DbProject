package fi.metropolia.ilkkas.demo.repository;

import fi.metropolia.ilkkas.demo.entity.SupplierAddresses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierAddressesRepository extends JpaRepository<SupplierAddresses, Integer> {
}
