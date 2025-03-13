package com.kids.collection.services;

import com.kids.collection.request.ProductRequest;
import com.kids.collection.response.ProductResponse;
import com.kids.collection.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository repository;

    @Transactional
    public Set<ProductResponse> findProducts() {
        Set<ProductResponse> productResponse = new HashSet<>();
        return productResponse;
    }

    @Transactional
    public ProductResponse createProduct(ProductRequest request){
        logger.debug("creating a product!");
        return new ProductResponse();
    }
}
