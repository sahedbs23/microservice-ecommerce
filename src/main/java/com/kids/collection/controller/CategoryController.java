package com.kids.collection.controller;

import com.kids.collection.request.CategoryRequest;
import com.kids.collection.response.CategoryResponse;
import com.kids.collection.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @GetMapping
    public Page<CategoryResponse> findCategories(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "0") int pageSize
    ){
        return service.findCategories(pageNumber, pageSize);
    }

    @PostMapping
    private ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest request){
        CategoryResponse response = service.createCategory(request);
        URI uri = URI.create("/categories/"+response.getId());
        return ResponseEntity.created(uri).build();
    }
}
