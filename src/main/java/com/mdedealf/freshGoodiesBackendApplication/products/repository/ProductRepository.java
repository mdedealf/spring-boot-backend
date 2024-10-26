package com.mdedealf.freshGoodiesBackendApplication.products.repository;

import com.mdedealf.freshGoodiesBackendApplication.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
