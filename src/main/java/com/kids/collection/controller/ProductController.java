package com.kids.collection.controller;

import com.kids.collection.entity.Product;
import com.kids.collection.request.ProductRequest;
import com.kids.collection.response.ProductResponse;
import com.kids.collection.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    private Set<ProductResponse> getProducts(){
       return service.findProducts();
    }

    @PostMapping
    private ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest){
        Product newProduct = new Product();
        return ResponseEntity.ok().body(new ProductResponse());
    }
}
