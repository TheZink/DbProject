package fi.metropolia.ilkkas.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fi.metropolia.ilkkas.demo.entity.Customers;
import fi.metropolia.ilkkas.demo.repository.CustomerRepository;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    private final CustomerRepository customerRepository;

    public CustomersController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity<Customers> getCustomerById(@PathVariable int id) {
        Optional<Customers> client = customerRepository.findById(id);
        if (client.isPresent()) {
            System.out.println(client.get());
            return ResponseEntity.ok(client.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<String> putCustomer(@PathVariable int id, @RequestBody Customers customer) {
        if (customerRepository.existsById(id)) {
            customerRepository.save(customer);
            return ResponseEntity.ok("Asiakas päivitetty onnistuneesti.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/post")
    public ResponseEntity<String> postCustomer(@RequestBody Customers customer) {
        try {
            customerRepository.save(customer);
            return ResponseEntity.ok("Asiakas tallennettu onnistuneesti.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Virhe: Asiakkaan ID on jo olemassa.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(required = false) int id, @RequestParam(required = false) Integer idParam) {

        int customerId = (idParam != null) ? idParam : id;

        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return ResponseEntity.ok("Asiakas poistettu onnistuneesti.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
