package com.mdedealf.freshGoodiesBackendApplication.favoriteProducts.controller;

import com.mdedealf.freshGoodiesBackendApplication.favoriteProducts.service.FavoriteProductService;
import com.mdedealf.freshGoodiesBackendApplication.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/favorites")
public class FavoriteProductController {

    private final FavoriteProductService favoriteProductService;

    public FavoriteProductController(FavoriteProductService favoriteProductService) {
        this.favoriteProductService = favoriteProductService;
    }

    @PostMapping("/toggle")
    public ResponseEntity<Response<Map<String, Object>>> toggleFavoriteProduct (
            @RequestParam Long userId,
            @RequestBody Map<String, Long> request
    ) {
        Long productId = request.get("productId");
        boolean isFavorite = favoriteProductService.toggleFavorite(userId, productId);

        if (productId == null) {
            return Response.failedResponse(HttpStatus.BAD_REQUEST.value(), "Product ID is required.");
        }

        Map<String, Object> responseData = Map.of(
                "isFavorite", isFavorite,
                "productId", productId
        );

        return Response.successfullyResponse(
                HttpStatus.OK.value(),
                "Favorite product toggled successfully",
                responseData
        );
    }
}
