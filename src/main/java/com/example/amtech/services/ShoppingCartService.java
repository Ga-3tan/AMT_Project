package com.example.amtech.services;

import com.example.amtech.models.Product;
import com.example.amtech.models.ShoppingCart;
import com.example.amtech.models.ShoppingCartRecord;
import com.example.amtech.repository.CustomShoppingCartRepository;
import com.example.amtech.repository.ProductRepository;
import com.example.amtech.repository.ShoppingCartRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpSession;
import java.util.Iterator;

/**
 * Service offering management of the session shopping cart and coherence with
 * the related shopping cart in the database.
 */
@Slf4j
@AllArgsConstructor
@Service
public class ShoppingCartService {

    private HttpSession httpSession;
    private ProductRepository productRepo;
    private ShoppingCartRepository shoppingCartRepo;
    private CustomShoppingCartRepository customShoppingCartRepo;
    private ProductService productService;

    public ShoppingCart checkCartIntegrity(ShoppingCart cart) {
        for (ShoppingCartRecord r : cart.getProducts().values()) {
            Product dbProduct = productRepo.findById(r.product.getId()).orElse(null);

            if (dbProduct == null) {
                cart.remove(r.product);
            } else if (!dbProduct.equals(r.product)) {
                int qty = r.quantity;
                cart.remove(r.product);
                cart.setProduct(dbProduct, qty);
            }
        }
        updateDbShoppingCart(cart);
        return cart;
    }

    public void updateDbShoppingCart(ShoppingCart cart) {
        Object userId = httpSession.getAttribute("user_id");
        if (userId != null) { // True when logged
            customShoppingCartRepo.updateShoppingCart(cart);
        }
    }

    public void updateSessionShoppingCartFromForm(ShoppingCart shoppingCart, MultiValueMap<String, String> formData) {
        // Updates all products values
        try {
            Iterator<String> prodIds = formData.get("form_product_id").iterator();
            Iterator<String> prodQty = formData.get("form_product_quantity").iterator();

            while (prodIds.hasNext()) {
                Product p = productService.getById(prodIds.next());
                if (p != null) {
                    int newQty = Integer.parseInt(prodQty.next());
                    shoppingCart.setProduct(p, newQty);
                    updateDbShoppingCart(shoppingCart);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public ShoppingCart retrieveUserShoppingCart(String userId) {
        ShoppingCart cart = shoppingCartRepo.findShoppingCartByUserId(userId);

        if (cart == null) { // User does not have a shopping cart
            cart = createShoppingCartInDb(userId);
        }

        return cart;
    }

    private ShoppingCart createShoppingCartInDb(String userId) {
        ShoppingCart cart = new ShoppingCart();
        cart.setUserId(userId);
        return shoppingCartRepo.save(cart);
    }
}
