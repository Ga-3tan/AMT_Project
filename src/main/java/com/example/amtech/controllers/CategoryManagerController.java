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
import java.util.List;

@AllArgsConstructor
@Controller
public class CategoryManagerController extends SessionController {

    private ProductService productService;
    private CategoryService categoryService;

    @ModelAttribute("prodService")
    public ProductService productService() {
        return productService;
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/manage-category")
    public String insertCategory(Model model) {
        model.addAttribute("category", new Category());
        return "manage-category";
    }

    @PostMapping("/manage-category")
    public String insertCategoryPost(@Valid @ModelAttribute Category category, BindingResult bindingResult, Model model) {
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
        String catName = categoryService.getById(id).getName();
        List<Product> products = productService.getProductsByCategory(catName);
        for (Product p : products){
            String[] cat = p.getCategory();
            cat = (String[]) ArrayUtils.removeElement(cat, catName);
            productService.updateProductCategories(p.getId(), cat);
        }
        categoryService.deleteById(id);
        return "redirect:/manage-category";
    }
}
