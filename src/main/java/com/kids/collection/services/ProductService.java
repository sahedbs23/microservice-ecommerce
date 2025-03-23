package com.kids.collection.services;

import com.kids.collection.entity.Product;
import com.kids.collection.entity.Tag;
import com.kids.collection.enums.ProductStatus;
import com.kids.collection.exception.RecordNotFoundException;
import com.kids.collection.repository.BrandRepository;
import com.kids.collection.repository.CategoryRepository;
import com.kids.collection.repository.TagRepository;
import com.kids.collection.request.ProductRequest;
import com.kids.collection.response.ProductResponse;
import com.kids.collection.repository.ProductRepository;
import com.kids.collection.response.TagResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final TagRepository tagRepository;

    @Transactional
    public Set<ProductResponse> findProducts() {

        Set<ProductResponse> productResponse = new HashSet<>();
        return productResponse;
    }

    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        Product product = repository.save(toProduct(request));
        return toProductResponse(product);
    }

    private Product toProduct(ProductRequest request) {
        Product product = new Product();

        product.setProductName(request.getName());
        product.setDescription(request.getDescription());
        product.setBrand(brandRepository.findById(request.getBrand()).orElseThrow(() -> new RecordNotFoundException(request.getBrand())));
        product.setCategory(categoryRepository.findById(request.getCategory()).orElseThrow(() -> new RecordNotFoundException(request.getBrand())));

        Set<Tag> tags = new HashSet<>(tagRepository.findAllById(request.getTags()));

        if (tags.size() != request.getTags().size()) {
            throw new RecordNotFoundException("tags are invalid");
        }
        product.setTags(tags);
        product.setStatus(ProductStatus.STATUS_PENDING.ordinal());

        return product;
    }

    private ProductResponse toProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getProductId());
        response.setProductName(product.getProductName());
        response.setDescription(product.getDescription());
        Set<TagResponse> tagResponses = product.getTags()
                .stream()
                .map(TagService::toTagResponse)
                .collect(Collectors.toSet());
        response.setTags(tagResponses);
        response.setBrand(BrandService.toBrandResponse(product.getBrand()));
        response.setCategory(CategoryService.toCategoryResponse(product.getCategory()));
        return response;
    }
}
