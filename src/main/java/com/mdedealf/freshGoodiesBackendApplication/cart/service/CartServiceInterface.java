package com.mdedealf.freshGoodiesBackendApplication.cart.service;

import com.mdedealf.freshGoodiesBackendApplication.cart.entity.Cart;
import com.mdedealf.freshGoodiesBackendApplication.cart.entity.CartItem;

import java.util.List;

public interface CartServiceInterface {

    CartItem addCartItem(Long cartId, Long productId, int quantity);
    List<CartItem> getAllCartItems(Long cartId);
    Cart getCartById(Long cartId);
    CartItem updateCartItem(Long cartId, Long itemId, int changesQuantity);
    void deleteCartItem(Long cartId, Long itemId);
    Cart createCart();
    void deleteCart(Long cartId);
}
