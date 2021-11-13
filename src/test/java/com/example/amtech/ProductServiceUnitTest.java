package com.example.amtech;

import com.example.amtech.models.Product;
import com.example.amtech.models.ProductService;
import com.example.amtech.repository.CustomProductRepository;
import com.example.amtech.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ProductServiceUnitTest {

    @Mock ProductRepository productRepository;
    @Mock CustomProductRepository customProductRepository;
    ProductService prodServ;

    @BeforeEach
    void setUp() {
        prodServ = new ProductService(productRepository, customProductRepository);
    }

    @Test
    public void it_should_create_Data() {
        Product p1 = new Product("1", "/img.png", "testProd1", "Try to create product 1", 5.5, 10,1, false, 0, new String[]{"high-tech", "cpu"});
        Mockito.when(productRepository.save(p1)).thenReturn(p1);
        String actual = prodServ.createProduct("1", "/img.png", "testProd1", "Try to create product 1", 5.5, 10,1, false, 0, new String[]{"high-tech", "cpu"});
        assertThat(actual).isEqualTo(p1.getId());
    }

    @Test
    public void it_should_get_all_products() {
        Product p1 = new Product("1", "/img.png", "testProd1", "Try to create product 1", 5.5, 10,1, false, 0, new String[]{"high-tech", "cpu"});
        Product p2 = new Product("2", "/img.png", "testProd2", "Try to create product 1", 2.5, 1,2, false, 0, new String[]{"cpu", "composant"});
        Product p3 = new Product("3", "/img.png", "testProd3", "Try to create product 1", 5, 4,3, true, 0.5, new String[]{"NaN"});
        List<Product> list = new ArrayList<>(List.of(p1, p2, p3));
        Mockito.when(productRepository.findAll()).thenReturn(list);
        assertThat(prodServ.getAllProducts()).isEqualTo(list);
    }

    @Test
    public void it_should_get_product_byId() {
        Product p1 = new Product("1", "/img.png", "testProd1", "Try to create product 1", 5.5, 10,1, false, 0, new String[]{"high-tech", "cpu"});
        Mockito.when(productRepository.findById("1")).thenReturn(java.util.Optional.of(p1));
        assertThat(prodServ.getById("1")).isEqualTo(java.util.Optional.of(p1).get());
    }

    @Test
    public void it_should_update_quantity() {
        Mockito.when(customProductRepository.updateProductQuantity("1", 2)).thenReturn(true);
        assertThat(prodServ.updateProductQuantity("1", 2)).isTrue();
    }

    @Test
    public void it_should_get_products_by_category() {
        Product p1 = new Product("1", "/img.png", "testProd1", "Try to create product 1", 5.5, 10,1, false, 0, new String[]{"high-tech", "cpu"});
        Product p2 = new Product("2", "/img.png", "testProd2", "Try to create product 1", 2.5, 1,2, false, 0, new String[]{"cpu", "composant"});
        List<Product> list = new ArrayList<>(List.of(p1, p2));
        Mockito.when(productRepository.allProductsFromCategory("cpu")).thenReturn(list);
        assertThat(prodServ.getProductsByCategory("cpu")).isEqualTo(list);
    }

    @Test
    public void it_should_delete_byId() {
        Mockito.doNothing().when(productRepository).deleteById("1");
        prodServ.deleteById("1");
        Mockito.verify(productRepository, Mockito.times(1)).deleteById("1");
    }

    @Test
    public void it_should_cleanDB() {
        Mockito.doNothing().when(productRepository).deleteAll();
        prodServ.deleteAllProduct();
        Mockito.verify(productRepository, Mockito.times(1)).deleteAll();
    }
}
