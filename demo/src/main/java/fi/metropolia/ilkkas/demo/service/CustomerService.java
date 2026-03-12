package fi.metropolia.ilkkas.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.metropolia.ilkkas.demo.dto.CustomerDto;
import fi.metropolia.ilkkas.demo.dto.AddressDto;
import fi.metropolia.ilkkas.demo.entity.Customers;
import fi.metropolia.ilkkas.demo.entity.CustomerAddresses;
import fi.metropolia.ilkkas.demo.repository.CustomerAddressesRepository;
import fi.metropolia.ilkkas.demo.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerAddressesRepository addressRepository;

    public CustomerService(CustomerRepository customerRepository, CustomerAddressesRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public Customers saveOrUpdateWithAddress(int id, CustomerDto dto) {
        Customers customer;
        if (customerRepository.existsById(id)) {
            customer = customerRepository.findById(id).orElse(new Customers());
        } else {
            customer = new Customers();
            customer.setId(id);
        }

        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());

        // Save customer first so it has an id (if new)
        customer = customerRepository.save(customer);

        AddressDto ad = dto.getAddress();
        if (ad != null) {
            CustomerAddresses ca = new CustomerAddresses();
            ca.setAddress(ad.getStreetAddress());
            ca.setPostalCode(ad.getPostalCode());
            ca.setCity(ad.getCity());
            ca.setCountry(ad.getCountry());
            ca.setCustomer(customer);
            ca = addressRepository.save(ca);

            // attach to customer collection if present
            try {
                customer.getAddresses().add(ca);
                customerRepository.save(customer);
            } catch (Exception e) {
                // ignore if addresses list not present
            }
        }

        return customer;
    }
}
