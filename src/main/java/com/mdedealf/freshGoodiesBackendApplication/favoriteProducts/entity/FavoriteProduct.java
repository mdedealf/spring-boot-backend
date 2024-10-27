package com.mdedealf.freshGoodiesBackendApplication.favoriteProducts.entity;

import com.mdedealf.freshGoodiesBackendApplication.products.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "favorites")
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
