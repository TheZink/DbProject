package fi.metropolia.ilkkas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fi.metropolia.ilkkas.demo.entity.CustomerAddresses;

public interface CustomerAddressesRepository extends JpaRepository<CustomerAddresses, Integer> {

}
