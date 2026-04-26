package com.ws101.Alastoy_Espano.EcommerceApi.controller;
import com.ws101.Alastoy_Espano.EcommerceApi.model.Product;
import com.ws101.Alastoy_Espano.EcommerceApi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product with ID " + id + " not found");
        }
        return ResponseEntity.ok(product);
    }

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
        if (product.getCategory() == null || product.getCategory().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Category is required");
        }
        if (product.getStockQuantity() < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Stock quantity must be non-negative");
        }
        Product created = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable int id,
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
                    .body("Product with ID" + id + "not found");
        }
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchProduct(
            @PathVariable int id,
            @RequestBody Product product) {
        Product patched = productService.patchProduct(id, product);
        if (patched == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product with ID" + id + "not found");
        }
        return ResponseEntity.ok(patched);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        boolean deleted = productService.deleteProduct(id);
        if(!deleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product with ID" + id + "not found");
        }
        return ResponseEntity.noContent().build();
    }

}