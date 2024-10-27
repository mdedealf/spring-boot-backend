package com.mdedealf.freshGoodiesBackendApplication.favoriteProducts.service;

import com.mdedealf.freshGoodiesBackendApplication.exceptions.DataNotFoundException;
import com.mdedealf.freshGoodiesBackendApplication.favoriteProducts.entity.FavoriteProduct;
import com.mdedealf.freshGoodiesBackendApplication.favoriteProducts.entity.User;
import com.mdedealf.freshGoodiesBackendApplication.favoriteProducts.repository.FavoriteProductRepository;
import com.mdedealf.freshGoodiesBackendApplication.favoriteProducts.repository.UserRepository;
import com.mdedealf.freshGoodiesBackendApplication.products.entity.Product;
import com.mdedealf.freshGoodiesBackendApplication.products.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoriteProductService implements FavoriteProductsInterface {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final FavoriteProductRepository favoriteProductRepository;

    public FavoriteProductService(UserRepository userRepository, ProductRepository productRepository,FavoriteProductRepository favoriteProductRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.favoriteProductRepository = favoriteProductRepository;
    }

    @Override
    @Transactional
    public boolean toggleFavorite(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User with ID : " +userId+ " not found."));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new DataNotFoundException("Product with ID : " +productId+ " not found."));

        Optional<FavoriteProduct> favoriteProductOptional = favoriteProductRepository.findByUserIdAndProductId(userId, productId);

        if (favoriteProductOptional.isPresent()) {
            // If favorite already exist, delete it
            favoriteProductRepository.delete(favoriteProductOptional.get());
            // Unmark as favorite
            return false;
        } else {
            // If favorite doesn't exist, create it
            FavoriteProduct newFavoriteProduct = new FavoriteProduct();
            newFavoriteProduct.setUser(user);
            newFavoriteProduct.setProduct(product);

            favoriteProductRepository.save(newFavoriteProduct);
            // Marked as favorite
            return true;
        }
    }
}
