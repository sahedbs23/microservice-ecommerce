package com.kids.collection.controller;

import com.kids.collection.request.ProductRequest;
import com.kids.collection.response.ProductResponse;
import com.kids.collection.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    private Set<ProductResponse> getProducts(){
       return service.findProducts();
    }

    @PostMapping
    private ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request){
        ProductResponse response = service.createProduct(request);
        return ResponseEntity.created(URI.create("/products"+response.getId())).body(response);
    }
}
