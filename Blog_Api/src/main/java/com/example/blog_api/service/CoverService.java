package com.example.blog_api.service;

import com.example.blog_api.model.Blog;
import org.springframework.stereotype.Service;


public interface CoverService {
    void deleteByBlogId(int blogId);
}
