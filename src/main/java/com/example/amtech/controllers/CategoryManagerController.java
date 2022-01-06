package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.Category;
import com.example.amtech.models.Product;
import com.example.amtech.services.CategoryService;
import com.example.amtech.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

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
            System.out.println("There was a error "+bindingResult);
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
        List<Product> products = productService.getProductsByCategory(catName);
        for (Product p : products) {
            String[] cat = Arrays.stream(p.getCategory()).filter(s -> !s.equals(catName)).toArray(String[]::new);
            productService.updateProductCategories(p.getId(), cat);
        }
        categoryService.deleteById(id);
        return "redirect:/admin/manage-categories";
    }
}
