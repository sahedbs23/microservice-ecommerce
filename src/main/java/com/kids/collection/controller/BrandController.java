package com.kids.collection.controller;

import com.kids.collection.entity.Brand;
import com.kids.collection.request.BrandRequest;
import com.kids.collection.response.BrandResponse;
import com.kids.collection.services.BrandService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/brands")
@AllArgsConstructor
public class BrandController {

    private final BrandService service;

    @GetMapping
    public ResponseEntity<Set<BrandResponse>> findBrands(@RequestParam(name = "name", required = false, defaultValue = "") String name){
        Set<BrandResponse> responses = service.findBrands(name.isBlank() ? null : name);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BrandResponse> create(@Valid @RequestBody BrandRequest request){
        BrandResponse response = service.createBrand(request);
        return  new ResponseEntity<>(response,HttpStatus.CREATED);

    }
}
