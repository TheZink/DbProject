package fi.metropolia.ilkkas.demo.repository;

import fi.metropolia.ilkkas.demo.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

public interface ProductRepository extends JpaRepository<Products, Integer>, ProductRepositoryCustom {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Products p SET p.price = p.price * :percentage")
    int increasePriceByPercentage(double percentage);
    
}
