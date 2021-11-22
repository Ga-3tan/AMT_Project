package com.example.amtech.controllers;

import com.example.amtech.models.Category;
import com.example.amtech.models.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
public class CategoryManagerController {

    private CategoryService categoryService;

    @GetMapping("/manage-category")
    public String insertCategory(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("catService", categoryService);
        return "manage-category";
    }

    @PostMapping("/insert-category")
    public String insertCategoryPost(@ModelAttribute Category category, BindingResult bindingResult, Model model) {
        model.addAttribute("category", category);

        // If an error occurs when parsing from post method
        if(bindingResult.hasErrors()){
            System.out.println("There was a error "+bindingResult);
            return "error";
        }

        categoryService.createCategory(category);
        return "redirect:/manage-category";
    }

    @DeleteMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable String id) {
        categoryService.deleteById(id);
        return "redirect:/manage-category";
    }
}
