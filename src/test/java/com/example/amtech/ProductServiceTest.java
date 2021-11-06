package com.example.amtech;

import com.example.amtech.models.ProductService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceTest {

    @Autowired
    ProductService prodServ;

    @Test
    @Order(1)
    public void it_should_create_Data() {
        prodServ.createProduct("1", "/img.png", "prod1", "Try to create product 1", 5.5, 10,1, false, 0, new String[]{"high-tech", "cpu"});
        prodServ.createProduct("2", "/img.png", "prod2", "Try to create product 1", 2.5, 1,2, false, 0, new String[]{"headphones"});
        prodServ.createProduct("3", "/img.png", "prod3", "Try to create product 1", 5, 4,3, true, 0.5, new String[]{"NaN"});
        prodServ.createProduct("4", "/img.png", "prod1", "Try to create product 1", 5.5, 10,3, false, 0, new String[]{"high-tech", "cpu"});
        prodServ.createProduct("5", "/img.png", "prod2", "Try to create product 1", 2.5, 1,5, false, 0, new String[]{"cpu", "keyboards"});
        prodServ.createProduct("6", "/img.png", "prod3", "Try to create product 1", 5, 4, 3,true, 0.5, new String[]{"NaN"});
        prodServ.createProduct("7", "/img.png", "prod1", "Try to create product 1", 5.5, 10, 4,false, 0, new String[]{"high-tech", "cpu"});
        prodServ.createProduct("8", "/img.png", "prod2", "Try to create product 1", 2.5, 1, 4,false, 0, new String[]{"tv", "lol"});
        prodServ.createProduct("9", "/img.png", "prod3", "Try to create product 1", 5, 4, 2,true, 0.5, new String[]{"NaN"});
        assertThat(prodServ.count()).isEqualTo(3);
    }

    @Test
    @Order(2)
    public void it_should_get_all_products() {
        String expected = "Product(id=1, img=/img.png, name=prod1, description=Try to create product 1, price=5.5, quantity=10, sale=false, salePercentage=0.0, category=[high-tech, cpu])\n" +
                "Product(id=2, img=/img.png, name=prod2, description=Try to create product 1, price=2.5, quantity=1, sale=false, salePercentage=0.0, category=[cpu, lol])\n" +
                "Product(id=3, img=/img.png, name=prod3, description=Try to create product 1, price=5.0, quantity=4, sale=true, salePercentage=0.5, category=[NaN])\n";
        StringBuilder actual = new StringBuilder();
        prodServ.getAllProducts().forEach(product -> actual.append(product).append("\n"));
        assertThat(actual.toString()).isEqualTo(expected);
    }

    @Test
    @Order(3)
    public void it_should_get_product_byId() {
        String expected = "Product(id=1, img=/img.png, name=prod1, description=Try to create product 1, price=5.5, quantity=10, sale=false, salePercentage=0.0, category=[high-tech, cpu])";
        String actual = prodServ.getById("1").toString();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @Order(4)
    public void it_should_update_quantity() {
        boolean actual = prodServ.updateProductQuantity("3", 8);
        assertThat(actual).isTrue();
        String expected = "Product(id=3, img=/img.png, name=prod3, description=Try to create product 1, price=5.0, quantity=8, sale=true, salePercentage=0.5, category=[NaN])";
        assertThat(prodServ.getById("3").toString()).isEqualTo(expected);
    }

    @Test
    @Order(5)
    public void it_should_get_products_by_category() {
        String expected = "Product(id=1, img=/img.png, name=prod1, description=Try to create product 1, price=5.5, quantity=10, sale=false, salePercentage=0.0, category=[high-tech, cpu])\n" +
                "Product(id=2, img=/img.png, name=prod2, description=Try to create product 1, price=2.5, quantity=1, sale=false, salePercentage=0.0, category=[cpu, lol])\n";
        StringBuilder actual = new StringBuilder();
        prodServ.getProductsByCategory("cpu").forEach(product -> actual.append(product).append("\n"));
        assertThat(actual.toString()).isEqualTo(expected);
    }

    @Test
    @Order(6)
    public void it_should_delete_byId() {
        prodServ.deleteById("3");
        assertThat(prodServ.count()).isEqualTo(2);
        String expected = "Product(id=1, img=/img.png, name=prod1, description=Try to create product 1, price=5.5, quantity=10, sale=false, salePercentage=0.0, category=[high-tech, cpu])\n" +
                "Product(id=2, img=/img.png, name=prod2, description=Try to create product 1, price=2.5, quantity=1, sale=false, salePercentage=0.0, category=[cpu, lol])\n";
        StringBuilder actual = new StringBuilder();
        prodServ.getAllProducts().forEach(product -> actual.append(product).append("\n"));
        assertThat(actual.toString()).isEqualTo(expected);
    }
/*
    @Test
    @Order(7)
    public void it_should_cleanDB() {
        prodServ.deleteAllProduct();
        assertThat(prodServ.count()).isEqualTo(0);
    }*/
}
