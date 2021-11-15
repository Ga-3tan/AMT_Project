package com.example.amtech.models;

import com.example.amtech.repository.CustomUserRepository;
import com.example.amtech.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepo;

    private CustomUserRepository customUserRepo;

    // CRUD operations

    // CREATE
    public String createUser(String id, String firstname, String lastname, String email) {
        User user1 = new User(id, firstname, lastname, email);
        System.out.println(user1);
        User user = userRepo.save(user1);
        return user.getId();
    }

    // READ
    public User getUserById(String id) {
        return userRepo.findUserById(id);
    }

    public User getUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    public ShoppingCart getUserShoppingCart(String id) {
        return customUserRepo.getShoppingCartFromUserById(id);
    }

    // UPDATE

    // DELETE

}
