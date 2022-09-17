package com.example.blog_api.service;

import com.example.blog_api.Repository.AuthorRepository;
import com.example.blog_api.model.Author;
import com.example.blog_api.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{
    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
