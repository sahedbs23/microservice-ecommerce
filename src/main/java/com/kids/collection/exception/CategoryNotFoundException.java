package com.kids.collection.exception;


public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(Long categoryId){
        super("Category not found with id: "+categoryId);
    }
}
