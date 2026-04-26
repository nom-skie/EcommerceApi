package com.ws101.Alastoy_Espano.EcommerceApi.service;

import com.ws101.Alastoy_Espano.EcommerceApi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private List<Product> productList = new ArrayList<>();
    private int idCounter = 1;

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

    public List<Product> getAllProducts(){
        return productList;
    }

    public Product getProductById(int id){
        return productList.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public Product createProduct(Product product){
        product.setId(idCounter++);
        productList.add(product);
        return product;
    }

    public Product updateProduct(int id, Product product){
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() ==id) {
                product.setId(id);
                productList.set(i, product);
                return product;
            }
        }
        return null;
    }

    public Product patchProduct(int id, Product product){
        Product existing = getProductById(id);
        if (existing == null) return null;

        if(product.getName() != null) existing.setName(product.getName());
        if(product.getDescription() != null) existing.setDescription(product.getDescription());
        if(product.getCategory() != null) existing.setCategory(product.getCategory());
        if(product.getPrice() > 0) existing.setPrice(product.getPrice());
        if(product.getStockQuantity() >= 0) existing.setStockQuantity(product.getStockQuantity());
        if(product.getImageUrl() != null) existing.setImageUrl(product.getImageUrl());

        return existing;
    }

    public boolean deleteProduct(int id){
        return productList.removeIf(p -> p.getId() == id);
    }

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

