package com.example.blog_api.service;


import com.example.blog_api.Repository.BlogRepository;
import com.example.blog_api.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService{
    @Autowired
    private BlogRepository blogRepository;


    public List<Blog> getAll() {
        return blogRepository.findAll();
    }


    public Optional<Blog> findById(int id) {
        return blogRepository.findById(id);
    }

    public List<Blog> listAllFilter(int id) {
        return blogRepository.getBlogByCategory_Id(id);
    }

    @Override
    public Blog insert(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public void update(int id, Blog blog) {
        Blog blog1 = blogRepository.findById(id).get();
        blog1.setTitle(blog.getTitle());
        blog1.setContent(blog.getContent());
        blog1.setAuthor(blog.getAuthor());
        blog1.setFiles(blog.getFiles());
        blog1.setCovers(blog.getCovers());
        blog1.setCategory(blog.getCategory());
        blogRepository.save(blog1);
    }

    @Override
    public void delete(int id) {
        blogRepository.deleteById(id);
    }

//    @Override
//    public List<Blog> findBySearch(String key) {
//        return blogRepository.findAllByTitleContainingOrAuthor_NameOrCategoryContainingIgnoreCase(key);
//    }

    @Override
    public List<Blog> findByAuthorName(String authorName) {
        return blogRepository.findBlogsByAuthor_Name(authorName);
    }

    public List<Blog> findBlogsBySearch(String key1, String key2, String key3){
        return blogRepository.findBlogsBySearch(key1,key2,key3);
    }


}
