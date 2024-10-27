package com.mdedealf.freshGoodiesBackendApplication.favoriteProducts.service;

import com.mdedealf.freshGoodiesBackendApplication.products.entity.Product;

import java.util.List;

public interface FavoriteProductsInterface {
    boolean toggleFavorite(Long userId, Long productId);
    List<Product> getAllFavoriteProducts(Long userId);
}
