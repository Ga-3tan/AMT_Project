package com.example.amtech.controllers;

import com.example.amtech.controllers.utils.SessionController;
import com.example.amtech.models.Product;
import com.example.amtech.services.CategoryService;
import com.example.amtech.services.ProductService;
import com.example.amtech.services.S3ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class ProductManagerController extends SessionController {

    private final ProductService productService;
    private final S3ImageService s3ImageService;

    public ProductManagerController(CategoryService categoryService, ProductService productService, S3ImageService s3ImageService) {
        super(categoryService);
        this.productService = productService;
        this.s3ImageService = s3ImageService;
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
            s3ImageService.uploadFile(multipartFile, product.getName());
            product.setImg(s3ImageService.getFileUrl(product.getName(), multipartFile.getOriginalFilename()));
        }

        try {
            productService.createProduct(product);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "insert-product";
        }

        return "redirect:/categories";
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

        productService.updateProduct(id, product); // TODO manage img update with s3
        return "redirect:/product/" + id;
    }
}
