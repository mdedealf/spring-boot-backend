package com.mdedealf.freshGoodiesBackendApplication.cart.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@Table(name = "cart")
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /*
        @OneToMany: Establishes a one-to-many relationship between Cart and CartItem. A cart can have multiple items.
        cascade = CascadeType.ALL: Propagates all operations (like persist, merge, remove) to CartItem entities.
        orphanRemoval = true: Automatically deletes CartItem entities that are removed from the items list.
        @JoinColumn(name = "cart_id"): Configures the foreign key column in the cart_item table as cart_id.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private List<CartItem> items;

}
