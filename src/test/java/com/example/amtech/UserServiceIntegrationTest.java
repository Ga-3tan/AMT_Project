package com.example.amtech;

import com.example.amtech.models.ProductService;
import com.example.amtech.models.ShoppingCart;
import com.example.amtech.models.UserService;
import com.example.amtech.repository.CustomUserRepository;
import com.example.amtech.repository.UserRepository;
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
@ContextConfiguration(initializers = {UserServiceIntegrationTest.Initializer.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
public class UserServiceIntegrationTest {

    @Container
    public static MongoDBContainer mongo = new MongoDBContainer("mongo");

    @Autowired
    UserService userServ;

    @Test
    @Order(1)
    public void it_should_create_User() {
        String id = userServ.createUser("1", "Gaetan", "Zwig", "gaetan.zwig@email.com");
        assertThat(id).isEqualTo("1");
        //assertThat(userServ.count()).isEqualTo(3);
    }

    @Test
    @Order(2)
    public void it_should_get_User_ShoppingCart() {
        ShoppingCart shoppingCart = userServ.getUserShoppingCart("1");
        System.out.println("Shopping Cart : " + shoppingCart);
        assert (true);
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
