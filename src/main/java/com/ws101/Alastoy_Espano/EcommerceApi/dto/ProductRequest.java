package com.ws101.Alastoy_Espano.EcommerceApi.dto;

import com.ws101.Alastoy_Espano.EcommerceApi.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Data Transfer Object for creating or updating a product.
 * Validates all incoming product data before it reaches the service layer.
 *
 * @author Alastoy, Españo
 */
public class ProductRequest {

    // Name must not be blank
    @NotBlank(message = "Product name is required")
    private String name;

    // Description is optional — no validation needed
    private String description;

    // Price must be a positive number greater than 0
    @Positive(message = "Price must be a positive number")
    private double price;

    // Category is required
    @NotNull(message = "Category is required")
    private Category category;

    // Stock must be 0 or more — cannot be negative
    @NotNull(message = "Stock quantity is required")
    @PositiveOrZero(message = "Stock quantity must be non-negative")
    private Integer stockQuantity;

    // Image URL is optional
    private String imageUrl;

    // ✅ Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}