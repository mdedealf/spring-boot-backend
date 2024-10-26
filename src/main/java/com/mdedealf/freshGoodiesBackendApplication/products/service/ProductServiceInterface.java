package com.mdedealf.freshGoodiesBackendApplication.products.service;

import com.mdedealf.freshGoodiesBackendApplication.products.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductServiceInterface {
    public List<Product> getProducts();
    public Optional<Product> getProduct();
    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public void deleteProduct(Long id);
}
