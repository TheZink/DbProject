package fi.metropolia.ilkkas.demo.service;

import org.springframework.stereotype.Service;

import fi.metropolia.ilkkas.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public int changePriceByPercentage(double percentage) {
        double multiplier = 1 + (percentage / 100);
        return productRepository.increasePriceByPercentage(multiplier);
    }
}
