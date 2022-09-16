package com.example.blog_api.service;


import com.example.blog_api.Repository.BlogRepository;
import com.example.blog_api.model.Blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface BlogService {
    public List<Blog> getAll();


    public Optional<Blog> findById(int id);

    public List<Blog> listAllFilter(int id);

    Blog insert(Blog blog);

    void update(int id, Blog blog);

    void delete(int id);



    List<Blog> findByAuthorName(String authorName);



    List<Blog> findBlogsBySearch(String key);
}


