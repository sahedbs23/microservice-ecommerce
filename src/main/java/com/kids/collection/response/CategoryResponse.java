package com.kids.collection.response;


import com.kids.collection.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class CategoryResponse {
    private int id;
    private String name;
    private String description;
    private Category parent;
}
