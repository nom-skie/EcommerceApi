package com.ws101.Alastoy_Espano.EcommerceApi.controller;

import com.ws101.Alastoy_Espano.EcommerceApi.model.Category;
import com.ws101.Alastoy_Espano.EcommerceApi.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing categories.
 *
 * @author Alastoy, Españo
 */
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    /**
     * Constructs a CategoryController.
     *
     * @param categoryRepository the category repository
     */
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Retrieves all categories.
     *
     * @return a list of all categories
     */
    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param id the category ID
     * @return the category, or 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new category.
     *
     * @param category the category data
     * @return the created category
     */
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Category name is required");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryRepository.save(category));
    }

    /**
     * Deletes a category by its ID.
     *
     * @param id the category ID
     * @return 204 No Content if deleted, or 404 if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        if (!categoryRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Category with ID " + id + " not found");
        }
        categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}