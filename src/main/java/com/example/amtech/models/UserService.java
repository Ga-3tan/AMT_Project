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
    public String createUser(String id, String username, String role) {
        User user1 = new User(id, username, role);
        System.out.println(user1);
        User user = userRepo.save(user1);
        return user.getId();
    }

    // READ
    public User getUserById(String id) {
        return userRepo.findUserById(id);
    }

    public User getUserByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }

    public ShoppingCart getUserShoppingCart(String id) {
        return customUserRepo.getShoppingCartFromUserById(id);
    }

    // UPDATE

    // DELETE

}
