package com.example.amtech;

import com.example.amtech.models.ProductService;
import com.example.amtech.repository.CustomProductRepository;
import com.example.amtech.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ServingWebContentApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServingWebContentApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }

    // TODO remove
    @Autowired ProductRepository productRepo;
    @Autowired CustomProductRepository customProductRepo;

    public void run(String... args) {
        ProductService productService = new ProductService(productRepo, customProductRepo);

        // Clean up any previous data
        productService.deleteAllProduct(); // Doesn't delete the collection

        System.out.println("-------------CREATE PRODUCT ITEMS-------------------------------\n");
        System.out.println(productService.createProduct("1", "/img.png", "testProd1", "Try to create product 1", 5.5, 10,1, false, 0, new String[]{"high-tech", "cpu"}));
        System.out.println(productService.createProduct("2", "/img.png", "testProd2", "Try to create product 1", 2.5, 0,2, false, 0, new String[]{"cpu", "composant"}));
        System.out.println(productService.createProduct("3", "/img.png", "testProd3", "Try to create product 1", 5, 4,3, true, 0.5, new String[]{"NaN"}));

        System.out.println("\n----------------SHOW ALL PRODUCT ITEMS---------------------------\n");
        productService.getAllProducts().forEach(System.out::println);
    }
}
