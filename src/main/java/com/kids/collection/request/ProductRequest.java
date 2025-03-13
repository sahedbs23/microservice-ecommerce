package com.kids.collection.request;

import com.kids.collection.annotation.ExistInDatabase;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class ProductRequest {
    @NotEmpty(message = "A product name must not empty")
    @Size(min = 5, max = 255, message = "Product name must be in between 5 and 255")
    private String name;

    @NotEmpty(message = "A product must contain some description")
    @Size(min = 150, max = 3000, message = "Give a nice product description within 150 to 3000")
    private String description;

    @ExistInDatabase(table = "brands", column = "id", message = "invalid brand id")
    private Long brand;

    @ExistInDatabase(table = "categories", column = "id", message = "invalid category id")
    private Long category;

    private Set<Long> tags;
}
