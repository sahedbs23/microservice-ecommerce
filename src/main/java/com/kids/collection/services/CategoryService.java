package com.kids.collection.services;

import com.kids.collection.entity.Category;
import com.kids.collection.exception.CategoryNotFoundException;
import com.kids.collection.repository.CategoryRepository;
import com.kids.collection.request.CategoryRequest;
import com.kids.collection.response.CategoryResponse;
import com.kids.collection.response.CategoryResponseWithParent;
import com.kids.collection.utils.Pagination;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public Set<CategoryResponseWithParent> findCategories(String categoryName) {
        Page<Category> categories = categoryName == null
                ? repository.findAll(Pagination.fixed("name"))
                : repository.findByNameLikeIgnoreCase(categoryName, Pagination.fixed("name"));

        return categories
                .stream()
                .map(CategoryService::toCategoryWithParentResponse)
                .collect(Collectors.toSet());
    }

    @Transactional
    public CategoryResponseWithParent createCategory(CategoryRequest request) throws CategoryNotFoundException {
        com.kids.collection.entity.Category category = repository.save(toCategory(request));
        return toCategoryWithParentResponse(category);
    }

    private Category toCategory(CategoryRequest request) {
        com.kids.collection.entity.Category category = new com.kids.collection.entity.Category();

        Long parentId = request.getParent();
        if (parentId != null) {
            Optional<com.kids.collection.entity.Category> parentCategory = repository.findById(parentId);
            if (parentCategory.isEmpty()) {
                throw new CategoryNotFoundException(parentId);
            }
            category.setParent(parentCategory.get());
        } else {
            category.setParent(null);
        }
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return category;
    }

    private static CategoryResponseWithParent toCategoryWithParentResponse(Category category) {
        Category parent = category.getParent();

        CategoryResponseWithParent response = new CategoryResponseWithParent(
                category.getId(),
                category.getName(),
                category.getDescription(),
                null

        );

        if (parent != null) {
            response.setParent(
                    new CategoryResponse(
                            parent.getId(),
                            parent.getName(),
                            parent.getDescription()
                    )
            );
        }

        return response;
    }

    public static CategoryResponse toCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );

    }
}
