package com.example.amtech.repository;

import com.example.amtech.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface AmtechRepository extends CrudRepository<Product, String> {

}
