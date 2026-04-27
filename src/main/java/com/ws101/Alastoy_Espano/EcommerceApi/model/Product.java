package com.ws101.Alastoy_Espano.EcommerceApi.model;

import lombok.*;

/**
 * Represents a product in the store.
 *
 * @author Alastoy, Españo
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class Product {

    /**
     * The product ID.
     */
    private Integer id;

    /**
     * The product name.
     */
    private String name;

    /**
     * The product description.
     */
    private String description;

    /**
     * The product price.
     */
    private double price;

    /**
     * The product category.
     */
    private String category;

    /**
     * The available stock quantity.
     */
    private Integer stockQuantity;

    /**
     * The URL of the product image.
     */
    private String imageUrl;
}
