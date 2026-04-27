package com.ws101.Alastoy_Espano.EcommerceApi.service;

import com.ws101.Alastoy_Espano.EcommerceApi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for managing product data in memory.
 *
 * @author Alastoy, Españo
 */
@Service
public class ProductService {

    private List<Product> productList = new ArrayList<>();
    private int idCounter = 1;

    /**
     * Initializes the service with sample products.
     */
    public ProductService() {
        productList.add(new Product(idCounter++, "Apple Airpods Pro 2", "Premium noise-cancelling earbuds", 12490.0, "Electronics", 50, "images/Apple AirPods Pro 2.jpg"));
        productList.add(new Product(idCounter++, "Air Max 270 React", "Lightweight running shoes", 10295.0, "Footwear", 30, "images/Air Max 270 React.jpg"));
        productList.add(new Product(idCounter++, "Automatic Umbrella", "Compact automatic open/close umbrella", 139.0, "Accessories", 100, "images/FK101 Automatic Umbrella.png"));
        productList.add(new Product(idCounter++, "ASUS TUF Gaming A16", "High performance gaming laptop", 88999.0, "Electronics", 10, "images/laptop.png"));
        productList.add(new Product(idCounter++, "NIVEA Body Lotion", "Moisturizing body lotion 400ml", 172.0, "Beauty", 75, "images/Nivea.jpg"));
        productList.add(new Product(idCounter++, "Denim Cap", "Denim cap with New York embroidered design", 133.0, "Accessories", 60, "images/Denim Cap with NEW YORK Embroidered Design.png"));
        productList.add(new Product(idCounter++, "Trolley Cart Organizer", "Multi-layer trolley storage cart", 224.0, "Home", 40, "images/trolly.png"));
        productList.add(new Product(idCounter++, "26 in 1 Wire Stripper", "Electrician hand tool set", 155.0, "Tools", 25, "images/26 in 1 Wire Stripper Electrician Hand Tool.png"));
        productList.add(new Product(idCounter++, "BAVIN PC091 20000mAh Power Bank", "Fast charging 20000mAh power bank", 336.41, "Electronics", 45, "images/powerbank.png"));
        productList.add(new Product(idCounter++, "Adidas Anti-Wind Jacket", "Running anti-wind fitting loose jacket", 1870.46, "Clothing", 20, "images/jacket.png"));
        productList.add(new Product(idCounter++, "BASH Gateway Weekday Backpack", "Stylish everyday backpack", 2495.0, "Bags", 15, "images/bag.png"));
        productList.add(new Product(idCounter++, "O.TWO.O Face Waterproof Powder", "Long lasting waterproof face powder", 242.0, "Beauty", 55, "images/face powder.png"));
    }

    /**
     * Retrieves all products.
     *
     * @return a List of all products
     */
    public List<Product> getAllProducts(){
        return productList;
    }

    /**
     * Finds a product by its ID.
     *
     * @param id the product ID to search for
     * @return the matching Product, or null if not found
     */
    public Product getProductById(Integer id){
        return productList.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    /**
     * Creates a new product and adds it to the list.
     *
     * @param product the product to create
     * @return the created product with its generated ID
     */
    public Product createProduct(Product product){
        product.setId(idCounter++);
        productList.add(product);
        return product;
    }

    /**
     * Replaces an existing product entirely.
     *
     * @param id      the ID of the product to replace
     * @param product the new product data
     * @return the updated product, or null if not found
     */
    public Product updateProduct(Integer id, Product product){
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId().equals(id)) {
                product.setId(id);
                productList.set(i, product);
                return product;
            }
        }
        return null;
    }

    /**
     * Partially updates a product's fields.
     *
     * @param id      the ID of the product to patch
     * @param product the fields to update (null fields are ignored)
     * @return the patched product, or null if not found
     */
    public Product patchProduct(Integer id, Product product){
        Product existing = getProductById(id);
        if (existing == null) return null;

        if(product.getName() != null) existing.setName(product.getName());
        if(product.getDescription() != null) existing.setDescription(product.getDescription());
        if(product.getCategory() != null) existing.setCategory(product.getCategory());
        if(product.getPrice() > 0) existing.setPrice(product.getPrice());
        if(product.getStockQuantity() != null && product.getStockQuantity() >= 0) existing.setStockQuantity(product.getStockQuantity());
        if(product.getImageUrl() != null) existing.setImageUrl(product.getImageUrl());

        return existing;
    }

    /**
     * Deletes a product by ID.
     *
     * @param id the ID of the product to delete
     * @return true if deleted, false if not found
     */
    public boolean deleteProduct(Integer id){
        return productList.removeIf(p -> p.getId().equals(id));
    }

    /**
     * Filters products by a given filter type and value.
     *
     * @param filterType  the field to filter by (name, category, price)
     * @param filterValue the value to match against
     * @return a List of matching products
     */
    public List<Product> filterProducts(String filterType, String filterValue){
        return productList.stream()
                .filter(p -> {
                    switch (filterType.toLowerCase()) {
                        case "name":
                            return p.getName().toLowerCase().contains(filterValue.toLowerCase());
                        case "category":
                            return p.getCategory().toLowerCase().contains(filterValue.toLowerCase());
                        case "price":
                            return p.getPrice() <= Double.parseDouble(filterValue);
                        default:
                            return false;
                    }
                })
                .toList();
    }

}

