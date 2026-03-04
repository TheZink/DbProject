package fi.metropolia.ilkkas.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

import fi.metropolia.ilkkas.demo.repository.ProductRepository;
import fi.metropolia.ilkkas.demo.service.ProductService;
import fi.metropolia.ilkkas.demo.entity.Products;


@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Products> getProductById(int id) {
        Optional<Products> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable int id, @RequestBody Products product) {
        Optional<Products> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            product.setId(id);
            Products updatedProduct = productRepository.save(product);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Products> createProduct(@RequestBody Products product) {
        Products savedProduct = productRepository.save(product);
        return ResponseEntity.ok(savedProduct);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.ok("Tuote poistettu onnistuneesti.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/bulk-modify-price/{percentage}")
    public ResponseEntity<String> increaseByPercentage(@PathVariable double percentage) {
        int updatedCount = productService.changePriceByPercentage(percentage);
        return ResponseEntity.ok("Päivitettyjen tuotteiden määrä: " + updatedCount);
    }
}