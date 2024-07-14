package com.example.ecommerce.Repository;

import com.example.ecommerce.entities.Categories;
import com.example.ecommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category = :categoryId")
    Page<Product> findByCategory(Long categoryId, Pageable pageable);
    List<Product> findByCategory(Categories category);
    // Example of sorting query
    @Query("SELECT p FROM Product p ORDER BY p.price DESC")
    List<Product> findAllByOrderByPriceDesc();
}
