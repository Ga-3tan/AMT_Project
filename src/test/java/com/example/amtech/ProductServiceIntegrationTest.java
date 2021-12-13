package com.example.amtech;

import com.example.amtech.services.ProductService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(initializers = {ProductServiceIntegrationTest.Initializer.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
public class ProductServiceIntegrationTest {

    @Container
    public static MongoDBContainer mongo = new MongoDBContainer("mongo");

    @Autowired
    ProductService prodServ;

    @Test
    @Order(1)
    public void it_should_create_Data() {
        prodServ.deleteAllProduct();
        String id = prodServ.createProduct("1", "/img.png", "testProd1", "Try to create product 1", 5.5, 10,1, false, 0, new String[]{"high-tech", "cpu"});
        assertThat(id).isEqualTo("1");
        prodServ.createProduct("2", "/img.png", "testProd2", "Try to create product 1", 2.5, 1,2, false, 0, new String[]{"cpu", "composant"});
        prodServ.createProduct("3", "/img.png", "testProd3", "Try to create product 1", 5, 4,3, true, 0.5, new String[]{"NaN"});
        assertThat(prodServ.count()).isEqualTo(3);
    }

    @Test
    @Order(2)
    public void it_should_get_all_products() {
        String expected = "Product(id=1, img=/img.png, name=testProd1, description=Try to create product 1, price=5.5, quantity=10, rating=1, sale=false, salePercentage=0.0, category=[high-tech, cpu])\n" +
                "Product(id=2, img=/img.png, name=testProd2, description=Try to create product 1, price=2.5, quantity=1, rating=2, sale=false, salePercentage=0.0, category=[cpu, composant])\n" +
                "Product(id=3, img=/img.png, name=testProd3, description=Try to create product 1, price=5.0, quantity=4, rating=3, sale=true, salePercentage=0.5, category=[NaN])\n";
        StringBuilder actual = new StringBuilder();
        prodServ.getAllProducts().forEach(product -> actual.append(product).append("\n"));
        assertThat(actual.toString()).isEqualTo(expected);
    }

    @Test
    @Order(3)
    public void it_should_get_product_byId() {
        String expected = "Product(id=1, img=/img.png, name=testProd1, description=Try to create product 1, price=5.5, quantity=10, rating=1, sale=false, salePercentage=0.0, category=[high-tech, cpu])";
        String actual = prodServ.getById("1").toString();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @Order(4)
    public void it_should_update_quantity() {
        boolean actual = prodServ.updateProductQuantity("3", 8);
        assertThat(actual).isTrue();
        String expected = "Product(id=3, img=/img.png, name=testProd3, description=Try to create product 1, price=5.0, quantity=8, rating=3, sale=true, salePercentage=0.5, category=[NaN])";
        assertThat(prodServ.getById("3").toString()).isEqualTo(expected);
    }

    @Test
    @Order(5)
    public void it_should_get_products_by_category() {
        String expected = "Product(id=1, img=/img.png, name=testProd1, description=Try to create product 1, price=5.5, quantity=10, rating=1, sale=false, salePercentage=0.0, category=[high-tech, cpu])\n" +
                "Product(id=2, img=/img.png, name=testProd2, description=Try to create product 1, price=2.5, quantity=1, rating=2, sale=false, salePercentage=0.0, category=[cpu, composant])\n";
        StringBuilder actual = new StringBuilder();
        prodServ.getProductsByCategory("cpu").forEach(product -> actual.append(product).append("\n"));
        assertThat(actual.toString()).isEqualTo(expected);
    }

    @Test
    @Order(6)
    public void it_should_delete_byId() {
        prodServ.deleteById("3");
        assertThat(prodServ.count()).isEqualTo(2);
        String expected = "Product(id=1, img=/img.png, name=testProd1, description=Try to create product 1, price=5.5, quantity=10, rating=1, sale=false, salePercentage=0.0, category=[high-tech, cpu])\n" +
                "Product(id=2, img=/img.png, name=testProd2, description=Try to create product 1, price=2.5, quantity=1, rating=2, sale=false, salePercentage=0.0, category=[cpu, composant])\n";
        StringBuilder actual = new StringBuilder();
        prodServ.getAllProducts().forEach(product -> actual.append(product).append("\n"));
        assertThat(actual.toString()).isEqualTo(expected);
    }

    @Test
    @Order(7)
    public void it_should_cleanDB() {
        prodServ.deleteAllProduct();
        assertThat(prodServ.count()).isEqualTo(0);
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.data.mongodb.uri=mongodb://root:example@localhost:27017/testAmtech",
                    "spring.data.mongodb.database=testAmtech"
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
