package fi.metropolia.ilkkas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fi.metropolia.ilkkas.demo.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Integer> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Products p SET p.price = p.price * :percentage")
    int increasePriceByPercentage(double percentage);
    
}
