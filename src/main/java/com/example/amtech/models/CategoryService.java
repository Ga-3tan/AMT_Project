package com.example.amtech.models;

import com.example.amtech.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Service
public class CategoryService {

    private CategoryRepository categoryRepo;

    // CRUD operations

    //CREATE
    public void createCategory(String id, String name) {
        categoryRepo.save(new Category(id, name));
    }
    public void createCategory(Category category) {categoryRepo.save(category);}

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
    public List<Category> getAllCategories() {
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
