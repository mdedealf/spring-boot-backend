package com.mdedealf.freshGoodiesBackendApplication.products.service;

import com.mdedealf.freshGoodiesBackendApplication.exceptions.DataNotFoundException;
import com.mdedealf.freshGoodiesBackendApplication.products.entity.Product;
import com.mdedealf.freshGoodiesBackendApplication.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceInterface {

    private final ProductRepository productRepository;

    /*
        Inject by constructor
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProduct(long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new DataNotFoundException("Product with ID " + id + " not found");
        }
        return product;
    }

    @Override
    public Product createProduct(Product product) {
        if(productRepository.existsById(product.getId())) {
            throw new DataNotFoundException("Product with ID " + product.getId() + " already exists");
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        if(!productRepository.existsById(product.getId())) {
            throw new DataNotFoundException("Product with ID " + product.getId() + " not exist.");
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if(!productRepository.existsById(id)) {
            throw new DataNotFoundException("Product with ID : " + id + " does not exist.");
        }
        productRepository.deleteById(id);
    }
}
