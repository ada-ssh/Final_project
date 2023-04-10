package com.dostavka.repository;

import com.dostavka.domain.Product;
import com.dostavka.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT p FROM Product p WHERE p.title=:title")
    Optional<Product> findProductByTitle(String title);
}
