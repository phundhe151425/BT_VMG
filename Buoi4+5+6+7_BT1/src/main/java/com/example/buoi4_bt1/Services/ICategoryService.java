package com.example.buoi4_bt1.Services;

import com.example.buoi4_bt1.Models.Blog;
import com.example.buoi4_bt1.Models.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> getAll();

    void save(Category category);

    void delete(Integer id);

    Optional<Category> findById(Integer id);
}
