package com.example.amtech.models;

import com.example.amtech.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service

// DPE - Les services dans le package model ? // TODO
public class CategoryService {

    private CategoryRepository categoryRepo;

    // CRUD operations

    //CREATE
    public void createCategory(String id, String name) {
        categoryRepo.save(new Category(id, name));
    }

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

    public boolean existsById(String id) {
        return categoryRepo.existsById(id);
    }

    public boolean existsByName(String name) {
        return getByName(name) != null;
    }

    public long count() {
        return categoryRepo.count();
    }

    //DELETE
    public void deleteAllCategories() {
        categoryRepo.deleteAll(); // Doesn't delete the collection
    }

    public void deleteById(String id) {
        categoryRepo.deleteById(id);
    }
}
