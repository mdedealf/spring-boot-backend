package com.mdedealf.freshGoodiesBackendApplication.products.controller;

import com.mdedealf.freshGoodiesBackendApplication.products.entity.Product;
import com.mdedealf.freshGoodiesBackendApplication.products.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable int id) {
        return "Product ID : "+id;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        var createdProduct = productService.createProduct(product);
        return createdProduct;
    }
}
