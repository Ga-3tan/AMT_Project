package com.example.amtech.models;

import com.example.amtech.repository.AmtechRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    @Autowired
    AmtechRepository amtechRepository;

    //save new product in the couchbase
    public void save(final Product e) {
        amtechRepository.save(e);
    }

    //get all product from the couchbase
    public List<Product> getProducts() {
        final Iterable<Product> productIterable = amtechRepository.findAll();
        return StreamSupport.stream(productIterable.spliterator(), false).collect(Collectors.toList());
    }

    //get product by id from the couchbase
    public Optional<Product> getProduct(final String id) {
        if (amtechRepository.existsById(id)) {
            return amtechRepository.findById(id);
        } else {
            return Optional.empty();
        }
    }
}
