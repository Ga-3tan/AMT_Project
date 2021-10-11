package com.example.amtech.controllers;

import com.example.amtech.models.Amtech;
import com.example.amtech.repository.AmtechRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AmtechController {

    @Autowired
    AmtechRepository amtechRepository;

    @RequestMapping("/amtech")
    public String index() {
        return "Welcome to AMTech";
    }

    @GetMapping("/amtech/{id}")
    public Optional<Amtech> getProduct(@PathVariable String id) {
        if (amtechRepository.existsById(id)) {
            return amtechRepository.findById(id);
        }
        else {
            return Optional.empty();
        }
    }


    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
