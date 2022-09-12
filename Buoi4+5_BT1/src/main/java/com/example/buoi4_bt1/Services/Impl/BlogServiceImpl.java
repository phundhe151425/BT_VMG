package com.example.buoi4_bt1.Services.Impl;

import com.example.buoi4_bt1.Models.Blog;
import com.example.buoi4_bt1.Repositories.BlogRepository;
import com.example.buoi4_bt1.Services.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements IBlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);

    }

    @Override
    public void delete(Integer id) {
        blogRepository.deleteById(id);

    }

    @Override
    public Optional<Blog> findById(Integer id) {
        return blogRepository.findById(id);
    }
}
