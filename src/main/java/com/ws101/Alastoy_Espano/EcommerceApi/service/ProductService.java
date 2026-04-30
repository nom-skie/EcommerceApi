package com.ws101.Alastoy_Espano.EcommerceApi.service;

import com.ws101.Alastoy_Espano.EcommerceApi.model.Product;
import com.ws101.Alastoy_Espano.EcommerceApi.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service for managing product data in memory.
 *
 * @author Alastoy, Españo
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;
    
    /**
     * Initializes the service with sample products.
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }    
    
    /**
     * Retrieves all products.
     *
     * @return a List of all products
     */
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    /**
     * Finds a product by its ID.
     *
     * @param id the product ID to search for
     * @return the matching Product, or null if not found
     */
    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    /**
     * Creates a new product and adds it to the list.
     *
     * @param product the product to create
     * @return the created product with its generated ID
     */
    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    /**
     * Replaces an existing product entirely.
     *
     * @param id      the ID of the product to replace
     * @param product the new product data
     * @return the updated product, or null if not found
     */
    public Product updateProduct(Long id, Product product){
        if(!productRepository.existsById(id)){
            return null;
        }
        return productRepository.save(product);
    }

    /**
     * Partially updates a product's fields.
     *
     * @param id      the ID of the product to patch
     * @param product the fields to update (null fields are ignored)
     * @return the patched product, or null if not found
     */
    public Product patchProduct(Long id, Product product){
        Product existing = getProductById(id);
        if (existing == null) return null;

        if(product.getName() != null) existing.setName(product.getName());
        if(product.getDescription() != null) existing.setDescription(product.getDescription());
        if(product.getCategory() != null) existing.setCategory(product.getCategory());
        if(product.getPrice() > 0) existing.setPrice(product.getPrice());
        if(product.getStockQuantity() != null && product.getStockQuantity() >= 0) existing.setStockQuantity(product.getStockQuantity());
        if(product.getImageUrl() != null) existing.setImageUrl(product.getImageUrl());

        return productRepository.save(existing);
    }

    /**
     * Deletes a product by ID.
     *
     * @param id the ID of the product to delete
     * @return true if deleted, false if not found
     */
    public boolean deleteProduct(Long id){
        if(!productRepository.existsById(id)){
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }

    /**
     * Filters products by a given filter type and value.
     *
     * @param filterType  the field to filter by (name, category, price)
     * @param filterValue the value to match against
     * @return a List of matching products
     */
    public List<Product> filterProducts(String filterType, String filterValue){
        switch (filterType.toLowerCase()){
            case "name":
                return productRepository.findAll().stream().filter(p -> p.getName().toLowerCase().contains(filterValue.toLowerCase())).toList();
            case "category":
                return productRepository.findByCategoryName(filterValue);
            case "price":
                return productRepository.findByPriceRange(0, Double.parseDouble(filterValue));
            default:
                throw new IllegalArgumentException("Invalid filter type " + filterType);
        }
    }

}

