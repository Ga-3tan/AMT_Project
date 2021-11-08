package com.example.amtech.models;

import com.example.amtech.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    // CRUD operations

    //CREATE
    public void createCategory(String id, String name) {
        categoryRepo.save(new Category(id, name));
    }

    // TODO for testing only, remove after
    public void createAllCategories() {
        List<Category> categoryList = new ArrayList<Category>(Arrays.asList(
            new Category("1", "audio"),
            new Category("2", "photo"),
            new Category("3", "jeux vidéo"),
            new Category("4", "téléphone portable"),
            new Category("5", "composant PC"),
            new Category("6", "ordinateur"),
            new Category("7", "logiciel"),
            new Category("8", "TV")
        ));
        categoryRepo.saveAll(categoryList);
    }

    // READ
    public List<Category> getAllProducts() {
        return categoryRepo.findAll();
    }

    public Category getById(String id) {
        return categoryRepo.findById(id).orElse(null);
    }

    public boolean existsById(String id) {
        return categoryRepo.existsById(id);
    }
    public boolean existsByName(String name) {
        return existsById(categoryRepo.findCategoryByName(name).getId());
    }

    //DELETE
    public void deleteAllCategories() {
        categoryRepo.deleteAll(); // Doesn't delete the collection
    }

    public void deleteById(String id) {
        categoryRepo.deleteById(id);
    }
}
