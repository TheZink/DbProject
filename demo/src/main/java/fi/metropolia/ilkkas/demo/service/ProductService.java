package fi.metropolia.ilkkas.demo.service;

import fi.metropolia.ilkkas.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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

    // @Transactional
    // public int changePriceByPercentage(double percent) {
    //     return productRepository.increasePricesByPercent(percent);
    // }
}
