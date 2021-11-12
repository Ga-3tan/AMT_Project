package com.example.amtech.controllers;

import com.example.amtech.models.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@Controller
public class ProductController {

    private ProductService productService;

    @GetMapping("/product/{id}")
    public String product(@PathVariable String id, Model model) {
        model.addAttribute("product",productService.getById(id));
        return "product";
    }
}
