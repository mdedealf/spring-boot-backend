package com.mdedealf.freshGoodiesBackendApplication.cart.repository;

import com.mdedealf.freshGoodiesBackendApplication.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE c.id = :cartId")
    Cart findActiveCartById(Long cartId);
}
