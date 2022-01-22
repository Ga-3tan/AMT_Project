package com.example.amtech.services;

import com.example.amtech.models.Category;
import com.example.amtech.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service offering categories management.
 * It provides principal methods for CRUD operations on categories.
 */
@AllArgsConstructor
@Service
public class CategoryService {

    private CategoryRepository categoryRepo;

    // CREATE
    public void createCategory(Category category) throws Exception {
        if (existsByName(category.getName())) {
            throw new Exception("Category already exists");
        }
        categoryRepo.save(category);
    }

    // READ
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public Category getById(String id) {
        return categoryRepo.findById(id).orElse(null);
    }
    public Category getByName(String name) {
        return categoryRepo.findCategoryByName(name).orElse(null);
    }

    public boolean existsByName(String name) {
        return getByName(name) != null;
    }

    //DELETE
    public void deleteById(String id) {
        categoryRepo.deleteById(id);
    }
}
