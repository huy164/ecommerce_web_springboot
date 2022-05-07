package com.backend.lavender.repositories;
import java.util.List;

import com.backend.lavender.models.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
}