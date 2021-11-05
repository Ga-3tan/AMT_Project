package com.example.amtech;

import com.example.amtech.models.ProductService;
import com.example.amtech.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = ProductRepository.class)
public class ServingWebContentApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServingWebContentApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }

    // to remove when done
    @Autowired
    ProductService productService;

    public void run(String... args) {

        // Clean up any previous data
        productService.deleteAllProduct(); // Doesn't delete the collection

        System.out.println("-------------CREATE PRODUCT ITEMS-------------------------------\n");
        productService.createGroceryItems();

        System.out.println("\n----------------SHOW ALL PRODUCT ITEMS---------------------------\n");
        productService.getAllProducts().forEach(System.out::println);

        System.out.println("\n----------------SHOW PRODUCT ID=1---------------------------\n");
        System.out.println(productService.getById("1"));

        System.out.println("\n----------------SHOW PRODUCT COUNT---------------------------\n");
        System.out.println(productService.count());

        System.out.println("\n----------------SHOW PRODUCT OF CPU CAT---------------------------\n");
        productService.getProductsByCategory("cpu").forEach(System.out::println);
    }

}
