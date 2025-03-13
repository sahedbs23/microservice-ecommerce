package com.kids.collection.services;

import com.kids.collection.entity.Category;
import com.kids.collection.exception.CategoryNotFoundException;
import com.kids.collection.repository.CategoryRepository;
import com.kids.collection.request.CategoryRequest;
import com.kids.collection.response.CategoryResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public Page<CategoryResponse> findCategories(int pageNumber, int pageSize) {
        Page<Category> brands =  repository.findAll(PageRequest.of(pageNumber,pageSize, Sort.by(Sort.Direction.fromString("desc"),"Name")));
        return brands.map(CategoryService::toCategoryResponse);
    }

    @Transactional
    public CategoryResponse createCategory(CategoryRequest request) throws CategoryNotFoundException{

        Category brand = repository.save(toCategory(request));
        return toCategoryResponse(brand);
    }

    private Category toCategory(CategoryRequest request){
        Category category = new Category();

        Long parentId = request.getParent();
        if(parentId != null){
            Optional<Category> parentCategory = repository.findById(parentId);
            if(parentCategory.isEmpty()){
                throw new CategoryNotFoundException(parentId);
            }
            category.setParent(parentCategory.get());
        }else {
            category.setParent(null);
        }
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return category;
    }

    private static CategoryResponse toCategoryResponse(Category category){
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getParent()
        );
    }
}
