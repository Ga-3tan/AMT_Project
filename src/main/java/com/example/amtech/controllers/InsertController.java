package com.example.amtech.controllers;

import com.example.amtech.models.Category;
import com.example.amtech.models.CategoryService;
import com.example.amtech.models.Product;
import com.example.amtech.models.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
public class InsertController {

    CategoryService categoryService;
    ProductService productService;

    @GetMapping("/insert-product")
    public String insertProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "insert-product";
    }

    @PostMapping("/insert-product")
    public String insertProductPost(@ModelAttribute Product product, BindingResult bindingResult, Model model) {
        model.addAttribute("product", product);

        // If an error occurs when parsing from post method
        if(bindingResult.hasErrors()){
            System.out.println("There was a error "+bindingResult);
            return "error";
        }

        System.out.println(product);//TODO DEBUG
        productService.createProduct(product);
        return "redirect:/category";
    }

    @GetMapping("/insert-category")
    public String insertCategory(Model model) {
        model.addAttribute("category", new Category());
        return "insert-category";
    }

    @PostMapping("/insert-category")
    public String insertCategoryPost(@Valid @ModelAttribute Category category, BindingResult bindingResult, Model model) {
        model.addAttribute("category", category);

        // If an error occurs when parsing from post method
        if(bindingResult.hasErrors()){
            System.out.println("There was a error "+bindingResult);
            return "insert-category";
        }

        Category c = (Category) bindingResult.getTarget();
        if(categoryService.getByName(c.getName()) != null) {
            model.addAttribute("error", "Category already exists");
            return "insert-category";
        }

        System.out.println(category);//TODO DEBUG
        categoryService.createCategory(category);
        return "redirect:/";
    }
}
