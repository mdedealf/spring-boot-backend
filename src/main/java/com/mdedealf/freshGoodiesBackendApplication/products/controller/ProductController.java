package com.mdedealf.freshGoodiesBackendApplication.products.controller;

import com.mdedealf.freshGoodiesBackendApplication.products.entity.Product;
import com.mdedealf.freshGoodiesBackendApplication.products.service.ProductService;
import com.mdedealf.freshGoodiesBackendApplication.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Response<List<Product>>> getProducts() {
        return Response.successfullyResponse(
            HttpStatus.OK.value(),
            "Products fetched successfully",
            productService.getProducts()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Optional<Product>>> getProduct(@PathVariable long id) {
        return Response.successfullyResponse(
                HttpStatus.OK.value(),
                "Product fetched successfully",
                productService.getProduct(id)
        );
    }

    @PostMapping
    public ResponseEntity<Response<Product>> createProduct(@RequestBody Product product) {
        var createdProduct = productService.createProduct(product);
        return Response.successfullyResponse(
                HttpStatus.CREATED.value(),
                "Successfully created a new product",
                createdProduct
        );
    }
}
