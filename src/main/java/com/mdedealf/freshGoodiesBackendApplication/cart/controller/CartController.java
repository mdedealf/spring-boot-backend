package com.mdedealf.freshGoodiesBackendApplication.cart.controller;

import com.mdedealf.freshGoodiesBackendApplication.cart.entity.Cart;
import com.mdedealf.freshGoodiesBackendApplication.cart.entity.CartItem;
import com.mdedealf.freshGoodiesBackendApplication.cart.service.CartService;
import com.mdedealf.freshGoodiesBackendApplication.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Response<Cart>> createCart(@RequestBody CartItem cartItem) {
        Cart cart = cartService.createCart();
        cartService.addCartItem(cart.getId(), cartItem.getProductId(), cartItem.getQuantity());
        return Response.successfullyResponse(HttpStatus.CREATED.value(),
                "Cart successfully created", cart);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<Response<CartItem>> addCartItem (@PathVariable Long cartId, @RequestBody CartItem cartItem) {
        CartItem addedItem = cartService.addCartItem(cartId, cartItem.getProductId(), cartItem.getQuantity());
        return Response.successfullyResponse(HttpStatus.CREATED.value(), "Cart item added.", addedItem);
    }

    @GetMapping("/{cartId}/items")
    public ResponseEntity<Response<List<CartItem>>> getAllCartItems(@PathVariable Long cartId) {
        List<CartItem> items = cartService.getAllCartItems(cartId);
        return Response.successfullyResponse("All cart items from ID: " +cartId+ " retrived", items);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Response<Cart>> getCartById(@PathVariable Long cartId) {
        Cart cart = cartService.getCartById(cartId);
        return Response.successfullyResponse("Cart retrived", cart);
    }

    @PutMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<Response<CartItem>> updateCartItem (@PathVariable Long cartId, @PathVariable Long itemId, @RequestBody CartItem cartItem) {
        CartItem updatedItem =  cartService.updateCartItem(cartId, itemId, cartItem.getQuantity());
        return Response.successfullyResponse("Cart item updated.", updatedItem);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Response<Void>> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return Response.successfullyResponse("Cart deleted.");
    }

    @DeleteMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<Response<Void>> deleteCartItem (@PathVariable Long cartId, @PathVariable Long itemId) {
        cartService.deleteCartItem(cartId, itemId);
        return Response.successfullyResponse("Cart item deleted.");
    }
}
