package com.kids.collection.services;

import com.kids.collection.entity.Brand;
import com.kids.collection.repository.BrandRepository;
import com.kids.collection.request.BrandRequest;
import com.kids.collection.response.BrandResponse;
import com.kids.collection.utils.Pagination;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandService {
    private static final Logger logger = LoggerFactory.getLogger(BrandService.class);

    private final BrandRepository repository;

    public Set<BrandResponse> findBrands(String name) {
        Page<Brand> brands = name == null
                ? repository.findAll(Pagination.fixed("name"))
                : repository.findByNameLikeIgnoreCase(name, Pagination.fixed("name"));

        return brands
                .stream()
                .map(BrandService::toBrandResponse)
                .collect(Collectors.toSet());
    }

    @Transactional
    public BrandResponse createBrand(BrandRequest request){
        Brand brand = repository.save(toBrand(request));
        return toBrandResponse(brand);
    }

    private static Brand toBrand(BrandRequest request){
        Brand brand = new Brand();
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        brand.setLogoUrl(request.getLogo());
        brand.setWebsite(request.getWebsite());
        return brand;
    }

    private static BrandResponse toBrandResponse(Brand brand){
        return new BrandResponse(
                brand.getId(),
                brand.getName(),
                brand.getDescription(),
                brand.getLogoUrl(),
                brand.getWebsite()
        );
    }
}
