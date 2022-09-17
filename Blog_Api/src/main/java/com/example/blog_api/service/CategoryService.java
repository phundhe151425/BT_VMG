package com.example.blog_api.service;

import com.example.blog_api.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    void save(Category category);

    void delete(Integer id);

    Optional<Category> findById(Integer id);
}
