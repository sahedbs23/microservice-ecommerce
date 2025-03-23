package com.kids.collection.controller;

import com.kids.collection.request.CategoryRequest;
import com.kids.collection.response.CategoryResponseWithParent;
import com.kids.collection.services.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @GetMapping
    public Set<CategoryResponseWithParent> findCategories(@RequestParam(name = "name", required = false, defaultValue = "") String name){
        return service.findCategories(name.isBlank() ? null : name);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<CategoryResponseWithParent> createCategory(@Valid @RequestBody CategoryRequest request){
        CategoryResponseWithParent response = service.createCategory(request);
        URI uri = URI.create("/categories/"+response.getId());
        return ResponseEntity.created(uri).build();
    }
}
