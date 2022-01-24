package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.Category;
import com.example.amtech.services.CategoryService;
import com.example.amtech.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller managing the admin page managing products categories.
 * It provides an endpoint to get all categories, to add a new category and
 * an endpoint to delete a category.
 */
@Controller
@RequestMapping("/admin")
public class CategoryManagerController extends SessionController {

    private final ProductService productService;

    public CategoryManagerController(CategoryService categoryService, ProductService productService) {
        super(categoryService);
        this.productService = productService;
    }

    @ModelAttribute("prodService")
    public ProductService productService() {
        return productService;
    }

    @GetMapping("/manage-categories")
    public String insertCategory(Model model) {
        model.addAttribute("category", new Category());
        return "manage-categories";
    }

    @PostMapping("/manage-categories")
    public String insertCategoryPost(@Valid @ModelAttribute Category category, BindingResult bindingResult, Model model) {
        // If an error occurs when parsing from post method
        if(bindingResult.hasErrors()){
            return "error";
        }

        try {
            categoryService.createCategory(category);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "manage-categories";
        }

        return "redirect:/admin/manage-categories";
    }

    @DeleteMapping("/manage-categories/delete/{id}")
    public String deleteCategory(@PathVariable String id) {
        String catName = categoryService.getById(id).getName();
        productService.removeCategoryFromProduct(catName);
        categoryService.deleteById(id);
        return "redirect:/admin/manage-categories";
    }
}
