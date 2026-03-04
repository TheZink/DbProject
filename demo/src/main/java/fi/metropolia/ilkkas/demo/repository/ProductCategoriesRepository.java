package fi.metropolia.ilkkas.demo.repository;

import fi.metropolia.ilkkas.demo.entity.ProductCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoriesRepository extends JpaRepository<ProductCategories, Integer> {

}
