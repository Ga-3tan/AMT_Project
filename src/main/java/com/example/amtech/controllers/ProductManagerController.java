package com.example.amtech.controllers;

import com.example.amtech.models.CategoryService;
import com.example.amtech.models.Product;
import com.example.amtech.models.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.testcontainers.shaded.org.apache.commons.io.FilenameUtils;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
@Controller
public class ProductManagerController {

    CategoryService categoryService;
    ProductService productService;

    @GetMapping("/insert-product")
    public String insertProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "insert-product";
    }

    @PostMapping("/insert-product")
    public String insertProductPost(@ModelAttribute Product product, BindingResult bindingResult, @RequestParam("image") MultipartFile multipartFile, Model model) {
        model.addAttribute("product", product);

        // If an error occurs when parsing from post method
        if(bindingResult.hasErrors()){
            System.out.println("There was a error " + bindingResult);
            return "error";
        }

        // Saves the image file
        if (!multipartFile.isEmpty()) {
            String imgDir = "images/product/";
            String dateName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
            String fileName = "img_" + dateName + "." + FilenameUtils.getExtension(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            try {
                saveFile(imgDir, fileName, multipartFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            product.setImg(fileName);
        }

        System.out.println(product);//TODO DEBUG
        productService.createProduct(product);
        return "redirect:/category";
    }


    @GetMapping("/update-product/{id}")
    public String updateQuantity(@PathVariable String id, Model model) {
        model.addAttribute("product", productService.getById(id));
        model.addAttribute("id",id);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "update-product";
    }

    @PostMapping("/update-product/{id}")
    public String updateQuantityPost(@PathVariable String id, @ModelAttribute Product product, BindingResult bindingResult, Model model) {
        model.addAttribute("product", product);

        // If an error occurs when parsing from post method
        if(bindingResult.hasErrors()){
            System.out.println("There was a error "+bindingResult);
            return "error";
        }

        productService.updateProduct(id, product);
        return "redirect:/product/" + id;
    }

    private static void saveFile(String uploadDir, String fileName,
                                 MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
}
