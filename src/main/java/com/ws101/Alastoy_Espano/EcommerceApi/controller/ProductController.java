package com.ws101.Alastoy_Espano.EcommerceApi.controller;
import com.ws101.Alastoy_Espano.EcommerceApi.model.Product;
import com.ws101.Alastoy_Espano.EcommerceApi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing products.
 *
 * @author Alastoy, Españo
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    /**
     * Constructs a ProductController.
     *
     * @param productService the product service
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieves all products.
     *
     * @return a list of all products
     */
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the product ID
     * @return the product, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product with ID " + id + " not found");
        }
        return ResponseEntity.ok(product);
    }

    /**
     * Filters products by name, category, or maximum price.
     *
     * @param filterType  the field to filter by (name, category, price)
     * @param filterValue the value to match
     * @return a list of matching products
     */
    @GetMapping("/filter")
    public ResponseEntity<?> filterProducts(
            @RequestParam String filterType,
            @RequestParam String filterValue) {
        try {
            return ResponseEntity.ok(productService.filterProducts(filterType, filterValue));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid filter: " + e.getMessage());
        }
    }

    /**
     * Creates a new product.
     *
     * @param product the product data
     * @return the created product, or 400 if validation fails
     */
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Product name is required");
        }
        if (product.getPrice() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Price must be a positive number");
        }
        if (product.getCategory() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Category is required");
        }
        if (product.getStockQuantity() == null || product.getStockQuantity() < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Stock quantity must be non-negative");
        }
        Product created = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Replaces an existing product.
     *
     * @param id      the product ID
     * @param product the new product data
     * @return the updated product, or 404 if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product){
        if (product.getName() == null || product.getName().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Product name is required");
        }
        if (product.getPrice() <=0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Price must be a positive number");
        }
        Product updated = productService.updateProduct(id, product);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product with ID " + id + "not found");
        }
        return ResponseEntity.ok(updated);
    }

    /**
     * Partially updates an existing product.
     *
     * @param id      the product ID
     * @param product the fields to update
     * @return the updated product, or 404 if not found
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> patchProduct(
            @PathVariable Long id,
            @RequestBody Product product) {
        Product patched = productService.patchProduct(id, product);
        if (patched == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product with ID " + id + "not found");
        }
        return ResponseEntity.ok(patched);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the product ID
     * @return 204 No Content if deleted, or 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        if(!deleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product with ID " + id + "not found");
        }

        return ResponseEntity.noContent().build();
    }

}