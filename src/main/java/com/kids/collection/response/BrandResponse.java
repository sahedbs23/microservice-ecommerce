package com.kids.collection.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class BrandResponse {
    private Long id;
    private String name;
    private String description;
    private String website;
    private String log;
}
