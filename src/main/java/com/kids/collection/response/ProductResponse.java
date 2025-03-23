package com.kids.collection.response;

import com.kids.collection.entity.Brand;
import com.kids.collection.entity.Category;
import com.kids.collection.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String productName;
    private String description;
    private CategoryResponse category;
    private BrandResponse brand;
    private Set<TagResponse> tags;
}
