package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.Category;
import com.example.amtech.models.Product;
import com.example.amtech.services.CategoryService;
import com.example.amtech.services.FileService;
import com.example.amtech.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class ProductManagerController extends SessionController {

    private CategoryService categoryService;
    private ProductService productService;
    private FileService fileService;

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/insert-product")
    public String insertProduct(Model model) {
        model.addAttribute("product", new Product());
        return "insert-product";
    }

    @PostMapping("/insert-product")
    public String insertProductPost(@Valid @ModelAttribute Product product, BindingResult bindingResult, @RequestParam("image") MultipartFile multipartFile, Model model) {
        model.addAttribute("product", product);

        // If an error occurs when parsing from post method
        if(bindingResult.hasErrors()){
            System.out.println("There was a error "+bindingResult);
            return "insert-product";
        }

        // Saves the image file
        if (!multipartFile.isEmpty()) {
            fileService.saveImgFile(multipartFile, product);
        }

        try {
            productService.createProduct(product);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "insert-product";
        }
        return "redirect:/category";
    }


    @GetMapping("/update-product/{id}")
    public String updateProduct(@PathVariable String id, Model model) {
        model.addAttribute("product", productService.getById(id));
        model.addAttribute("id",id);
        return "update-product";
    }

    @PostMapping("/update-product/{id}")
    public String updateProductPost(@PathVariable String id, @ModelAttribute Product product, BindingResult bindingResult, Model model) {
        model.addAttribute("product", product);
        model.addAttribute("id",id);

        // If an error occurs when parsing from post method
        if(bindingResult.hasErrors()){
            System.out.println("There was a error "+bindingResult);
            return "error";
        }

        productService.updateProduct(id, product);
        return "redirect:/product/" + id;
    }
}
