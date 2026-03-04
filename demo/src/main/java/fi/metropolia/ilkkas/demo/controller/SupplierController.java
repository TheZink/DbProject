package fi.metropolia.ilkkas.demo.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.metropolia.ilkkas.demo.repository.SupplierRepository;
import fi.metropolia.ilkkas.demo.entity.Supplier;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
    private final SupplierRepository supplierRepository;

    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable int id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isPresent()) {
            return ResponseEntity.ok(supplier.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<?> putSupplierById(@PathVariable int id, @RequestBody Supplier supplier) {
        if (supplierRepository.existsById(id)) {
            supplier.setId(id);
            supplierRepository.save(supplier);
            return ResponseEntity.ok("Tavaratoimittajan päivittäminen onnistui.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> postSupplier(@RequestBody Supplier supplier) {
        supplierRepository.save(supplier);
        return ResponseEntity.ok("Tavaratoimittajan tallentaminen onnistui.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSupplierById(@PathVariable(required = false) int id, @RequestParam(required = false) Integer idParam) {
        
        int supplierId = (idParam != null) ? idParam : id;
        
        if (supplierRepository.existsById(supplierId)) {
            supplierRepository.deleteById(supplierId);
            return ResponseEntity.ok("Tavaratoimittajan poisto onnistui.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
