package com.mdedealf.freshGoodiesBackendApplication.favoriteProducts.repository;

import com.mdedealf.freshGoodiesBackendApplication.favoriteProducts.entity.FavoriteProduct;
import com.mdedealf.freshGoodiesBackendApplication.favoriteProducts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Long> {
    Optional<FavoriteProduct> findByUserIdAndProductId(Long userId, Long productId);
    List<FavoriteProduct> findByUser(User user);
}
