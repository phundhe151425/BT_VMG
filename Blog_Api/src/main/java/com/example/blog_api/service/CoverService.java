package com.example.blog_api.service;

import com.example.blog_api.model.Blog;
import com.example.blog_api.model.Cover;
import org.springframework.stereotype.Service;


public interface CoverService {
    void deleteByBlogId(int blogId);

    public Cover save(Cover cover);
}
