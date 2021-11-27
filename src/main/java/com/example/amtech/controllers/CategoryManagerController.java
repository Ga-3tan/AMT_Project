package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.Category;
import com.example.amtech.models.CategoryService;
import com.example.amtech.models.Product;
import com.example.amtech.models.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.testcontainers.shaded.org.apache.commons.lang.ArrayUtils;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Controller
public class CategoryManagerController extends SessionController {

    private ProductService productService;
    private CategoryService categoryService;

    @GetMapping("/manage-category")
    public String insertCategory(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categories);

        HashMap<String, Integer> categoriesCounter = new HashMap<>();
        for (Category cat : categories){
            List<Product> filteredProducts = productService.getProductsByCategory(cat.getName());
            categoriesCounter.put(cat.getId(), filteredProducts.size());
        }
        model.addAttribute("counter", categoriesCounter);



        return "manage-category";
    }

    @PostMapping("/manage-category")
    public String insertCategoryPost(@Valid @ModelAttribute Category category, BindingResult bindingResult, Model model) {
        model.addAttribute("category", category);
        List<Category> categories = categoryService.getAllCategories();
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categories);

        HashMap<String, Integer> categoriesCounter = new HashMap<>();

        for (Category cat : categories){
            List<Product> filteredProducts = productService.getProductsByCategory(cat.getName());
            categoriesCounter.put(cat.getId(), filteredProducts.size());
        }
        model.addAttribute("counter", categoriesCounter);

        // If an error occurs when parsing from post method
        if(bindingResult.hasErrors()){
            System.out.println("There was a error "+bindingResult);
            return "error";
        }

        if(categoryService.existsByName(category.getName())) {
            model.addAttribute("error", "Category already exists");
            return "manage-category";
        }

        categoryService.createCategory(category);
        return "redirect:/manage-category";
    }

    @DeleteMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable String id) {
        Category category = categoryService.getById(id);
        List<Product> products = productService.getProductsByCategory(category.getName());
        for (Product p : products){
            String[] cat = p.getCategory();
            cat = (String[]) ArrayUtils.removeElement(cat, category.getName());
            p.setCategory(cat);
            productService.updateProduct(p.getId(),p);
        }
        categoryService.deleteById(id);
        return "redirect:/manage-category";
    }
}
