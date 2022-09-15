package com.example.blog_api.Exceptions;

public class BlogNotFoundException extends RuntimeException{

    public BlogNotFoundException(int id){
        super(String.format("Blog with Id %d not found", id));
    }
}
