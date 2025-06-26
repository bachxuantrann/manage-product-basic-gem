package com.gem_intern.manage_product.repository;

import com.gem_intern.manage_product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String name);

    @Query("""
            select p from Product p where (:name is null or length(trim(:name)) = 0) or lower(p.name) like %:name%""")
    List<Product> findByName(String name);
}
