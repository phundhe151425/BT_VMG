package com.example.blog_api.service;

import com.example.blog_api.model.Author;
import com.example.blog_api.model.Category;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
