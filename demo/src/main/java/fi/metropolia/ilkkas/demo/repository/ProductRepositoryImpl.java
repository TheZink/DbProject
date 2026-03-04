package fi.metropolia.ilkkas.demo.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;
import fi.metropolia.ilkkas.demo.entity.Products;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int increasePricesByPercent(double percent) {

        double multiplier = 1 + (percent / 100.0);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Products> update = cb.createCriteriaUpdate(Products.class);
        Root<Products> root = update.from(Products.class);

        BigDecimal multiplierBD = BigDecimal.valueOf(multiplier);

        update.set(root.get("price"), cb.prod(root.get("price"), multiplierBD));

        return entityManager.createQuery(update).executeUpdate();
    }
}