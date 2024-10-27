package com.mdedealf.freshGoodiesBackendApplication.cart.service;

import com.mdedealf.freshGoodiesBackendApplication.cart.entity.Cart;
import com.mdedealf.freshGoodiesBackendApplication.cart.entity.CartItem;
import com.mdedealf.freshGoodiesBackendApplication.cart.repository.CartRepository;
import com.mdedealf.freshGoodiesBackendApplication.exceptions.DataNotFoundException;
import com.mdedealf.freshGoodiesBackendApplication.products.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements CartServiceInterface {

    private final ProductService productService;
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    @Override
    public CartItem addCartItem(Long cartId, Long productId, int quantity) {
        productService.getProduct(productId);

        Cart cart = cartRepository.findActiveCartById(cartId);

        if (cart == null) throw new DataNotFoundException("Cart not found");
        if (cart.getItems() == null) cart.setItems(new ArrayList<>());

        boolean itemExist = cart.getItems().stream()
                .anyMatch(item -> item.getProductId() == productId);

        if (itemExist) throw new DataNotFoundException("Item already exist in the cart, try to edit the quantity.");
        if (quantity <= 0) throw new DataNotFoundException("Quantity must be greater than 0");

        CartItem cartItem = new CartItem();
        cartItem.setProductId(productId);
        cartItem.setQuantity(quantity);
        cart.getItems().add(cartItem);

        cartRepository.save(cart);
        return cartItem;
    }

    @Override
    public List<CartItem> getAllCartItems(Long cartId) {
        Cart cart = cartRepository.findActiveCartById(cartId);
        if (cart == null) throw new DataNotFoundException("Cart not found.");
        return cart.getItems();
    }

    @Override
    public Cart getCartById(Long cartId) {
        Cart cart = cartRepository.findActiveCartById(cartId);
        if (cart == null) throw new DataNotFoundException("Cart not found.");
        return cart;
    }

    @Override
    @Transactional
    public CartItem updateCartItem(Long cartId, Long itemId, int changesQuantity) {
        Cart cart = cartRepository.findActiveCartById(cartId);

        if (cart == null) throw new DataNotFoundException("Cart not found");

        Optional<CartItem> cartItemOptional = cart.getItems().stream()
                .filter(item -> item.getId() == itemId)
                .findFirst();

        if (cartItemOptional.isEmpty()) throw new DataNotFoundException("CartItem not found.");

        CartItem cartItem = cartItemOptional.get();
        int newQuantity = cartItem.getQuantity() + changesQuantity;

        if (newQuantity <= 0) cart.getItems().remove(cartItem);
        else cartItem.setQuantity(newQuantity);

        return cartItem;
    }

    @Override
    public void deleteCartItem(Long cartId, Long itemId) {
        Cart cart = cartRepository.findActiveCartById(cartId);
        if (cart == null) throw new DataNotFoundException("Cart not found.");
        cart.getItems().removeIf(item -> item.getId() == itemId);

        if (cart.getItems().isEmpty()) cartRepository.deleteById(cartId);
        else cartRepository.save(cart);
    }

    @Override
    public Cart createCart() {
        Cart cart = new Cart();
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public void deleteCart(Long cartId) {
        Cart cart = cartRepository.findActiveCartById(cartId);
        if (cart == null) throw new DataNotFoundException("Cart not found");
        cartRepository.deleteById(cartId);
    }
}
