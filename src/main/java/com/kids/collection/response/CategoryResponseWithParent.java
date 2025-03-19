package com.kids.collection.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryResponseWithParent {
    private int id;
    private String name;
    private String description;
    private CategoryResponse parent;
}
