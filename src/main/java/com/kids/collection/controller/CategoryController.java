package com.kids.collection.controller;

import com.kids.collection.request.CategoryRequest;
import com.kids.collection.response.CategoryResponse;
import com.kids.collection.services.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    public Set<CategoryResponse> findCategories(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize,
            @RequestParam(name = "name") String name
    ){
        return service.findCategories(name, pageNumber, pageSize);
    }

    @PostMapping
    private ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest request){
        CategoryResponse response = service.createCategory(request);
        URI uri = URI.create("/categories/"+response.getId());
        return ResponseEntity.created(uri).build();
    }
}
