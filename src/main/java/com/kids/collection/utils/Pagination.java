package com.kids.collection.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class Pagination {
    public static PageRequest fixed(int pageNumber, int pageSize, Sort sort){
        return PageRequest.of(pageNumber, pageSize, sort);
    }

    public static PageRequest fixed(String sortProperties){
        int pageNumber = 0;
        int pageSize = 20;
        return fixed(pageNumber, pageSize, Sort.by(sortProperties).ascending());
    }
}
