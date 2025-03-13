package com.kids.collection.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryRequest {
    @NotEmpty(message = "Category name can't be empty")
    @Size(min = 5, max = 55)
    private String name;
    private Long parent;
    private String description;
}
