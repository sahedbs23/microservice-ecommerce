package com.kids.collection.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {
    @NotBlank(message = "A product name must not empty")
    @Size(min = 5, max = 255, message = "Product name must be in between 5 and 255")
    private String name;

    @NotBlank(message = "A product must contain some description")
    @Size(min = 150, max = 3000, message = "Give a nice product description within 150 to 3000")
    private String description;

    private Long brand;

    private Long category;

    private Set<Long> tags;
}
