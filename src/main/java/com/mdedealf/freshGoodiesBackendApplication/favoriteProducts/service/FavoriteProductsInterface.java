package com.mdedealf.freshGoodiesBackendApplication.favoriteProducts.service;

public interface FavoriteProductsInterface {
    boolean toggleFavorite(Long userId, Long productId);
}
